package com.example.ptp08_2072008.controller;

import com.example.ptp08_2072008.dao.categoryDao;
import com.example.ptp08_2072008.dao.itemDao;
import com.example.ptp08_2072008.model.KategoriEntity;
import com.example.ptp08_2072008.model.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class kategoriController {
    private ObservableList<KategoriEntity> katList;

    @FXML
    public TextField txtId;
    @FXML
    public TextField txtName;
    @FXML
    public Button btnSave;
    public TableView tableView;
    @FXML
    public TableColumn colId;
    @FXML
    public TableColumn colKat;

    private ObservableList <ItemEntity> itemList;
    categoryDao katDao = new categoryDao();
    itemDao itemDao = new itemDao();
    @FXML
    private Stage stage;
    @FXML
    private FXMLLoader fxmlLoader;

    public void tampilan(){
        katList = FXCollections.observableArrayList(katDao.getData());
        tableView.setItems(katList);
        colId.setCellValueFactory(new PropertyValueFactory<>("idKategori"));
        colKat.setCellValueFactory(new PropertyValueFactory<>("kategori"));

    }

    public void initialize(){
        tampilan();
    }

    public void addData(ActionEvent actionEvent) {
        if((txtId.getText().equals("") || (txtName.getText().equals("")))){
            alert();
        }else {
            int id = Integer.parseInt(txtId.getText());
            String nama = txtName.getText();
            KategoriEntity kat = new KategoriEntity();
            kat.setIdKategori(id);
            kat.setKategori(nama);
            katDao.setData(kat);
            reset();
        }
        tampilan();
    }

    public void alert (){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please Fill the Field Properly", ButtonType.OK);
        alert.showAndWait();
    }

    public void reset(){
        txtName.clear();
        txtId.clear();

    }

    public ObservableList<KategoriEntity> getKatList() {
        return katList;
    }

    public void setKatList(ObservableList<KategoriEntity> katList) {
        this.katList = katList;
    }
}
