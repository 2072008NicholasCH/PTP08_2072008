package com.example.ptp08_2072008.controller;

import com.example.ptp08_2072008.HelloApplication;
import com.example.ptp08_2072008.model.KategoriEntity;
import com.example.ptp08_2072008.model.ItemEntity;
import com.example.ptp08_2072008.util.utilConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.ptp08_2072008.dao.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class mainController {
    public MenuBar menuBar;
    @FXML
    public TableView<ItemEntity> tableView;
    @FXML
    public TableColumn<Object, Object> colId;
    @FXML
    public TableColumn<Object, Object> colName;
    @FXML
    public TableColumn<Object, Object> colPrice;
    @FXML
    public TableColumn<Object, Object> colKat;
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtPrice;
    @FXML
    public TextArea txtaDesc;
    @FXML
    public ComboBox<KategoriEntity> cBoxKat;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDel;
    ObservableList <KategoriEntity> katList;
    ObservableList <ItemEntity> itemList;
    categoryDao katDao = new categoryDao();
    itemDao itemDao = new itemDao();
    @FXML
    private Stage stage;
    @FXML
    private FXMLLoader fxmlLoader;
    private ObservableList<KategoriEntity> namaKategori;

    public void initialize() throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("category-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage = new Stage();
        stage.setTitle("Category Management");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        btnDel.setDisable(true);
        btnUpdate.setDisable(true);
        itemList = FXCollections.observableArrayList();
        tampilan();
    }

    public void getSelectedItem() {
        txtId.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getIdItem()));
        txtName.setText(tableView.getSelectionModel().getSelectedItem().getNama());
        txtPrice.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getPrice()));
        txtaDesc.setText(tableView.getSelectionModel().getSelectedItem().getDescription());
        cBoxKat.setValue(tableView.getSelectionModel().getSelectedItem().getKategoriByKategoriIdKategori());
        txtId.setDisable(true);
        btnSave.setDisable(true);
        btnDel.setDisable(false);
        btnUpdate.setDisable(false);
    }

    public void addData() {

        if ((txtId.getText().equals("")) || (txtPrice.getText().equals("")) || (txtName.getText().equals("")) || (txtaDesc.getText().equals("")) || (cBoxKat.getValue() == null)){
            alert();
        } else {
            int id = Integer.parseInt(txtId.getText());
            String nama = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String desc = txtaDesc.getText();
            KategoriEntity cBox = cBoxKat.getValue();

            ItemEntity item = new ItemEntity();
            item.setIdItem(id);
            item.setDescription(desc);
            item.setNama(nama);
            item.setPrice(price);
            item.setKategoriByKategoriIdKategori(cBox);
            itemDao.setData(item);
            reset();
        }
        tampilan();

    }

    public void alert (){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please Fill the Field Properly", ButtonType.OK);
        alert.showAndWait();
    }

    public void reset() {
        txtaDesc.clear();
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        cBoxKat.setValue(null);
        btnSave.setDisable(false);
        txtId.setDisable(false);
        btnDel.setDisable(true);
        btnUpdate.setDisable(true);
    }

    public void upData() {
        int id = Integer.parseInt(txtId.getText());
        String nama = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String desc = txtaDesc.getText();
        KategoriEntity cBox = cBoxKat.getValue();

        ItemEntity item = new ItemEntity();
        item.setIdItem(id);
        item.setDescription(desc);
        item.setNama(nama);
        item.setPrice(price);
        item.setKategoriByKategoriIdKategori(cBox);
        itemDao.upData(item);
        tampilan();
        reset();
    }

    public void delData() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete this selected data?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            itemDao.delData(tableView.getSelectionModel().getSelectedItem());
            tampilan();
            reset();
        }
    }

    public void tampilan(){
        itemList.clear();
        itemList = FXCollections.observableArrayList(itemDao.getData());
        tableView.setItems(itemList);
        colId.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colKat.setCellValueFactory(new PropertyValueFactory<>("KategoriByKategoriIdKategori"));
        katList = FXCollections.observableArrayList(katDao.getData());
        cBoxKat.setItems(katList);
    }

    public void gotoKategori() {
        stage.showAndWait();
        kategoriController kControl = fxmlLoader.getController();
        katList = kControl.getKatList();
        namaKategori = FXCollections.observableArrayList();
        cBoxKat.setItems(namaKategori);
        cBoxKat.getSelectionModel().select(0);
        tampilan();
    }

    public void gotoClose(ActionEvent actionEvent) {
        txtaDesc.getScene().getWindow().hide();
    }

    public void gotoSpRep(ActionEvent actionEvent) {
        Connection conn = utilConnection.getConnection();
        JasperPrint jp;
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/SimpleGroup_PTP06.jasper", param, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Simple Report");
            jv.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void gotoGrRep(ActionEvent actionEvent) {
        Connection conn = utilConnection.getConnection();
        JasperPrint jp;
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/GroupReport_PTP06.jasper", param, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Group Report");
            jv.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
