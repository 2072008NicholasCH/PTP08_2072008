module com.example.ptp08_2072008 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;

    exports com.example.ptp08_2072008;
    opens com.example.ptp08_2072008.model;
    exports com.example.ptp08_2072008.model;
    opens com.example.ptp08_2072008.dao;
    exports com.example.ptp08_2072008.dao to javafx.fxml;
    opens com.example.ptp08_2072008.util;
    exports com.example.ptp08_2072008.util to javafx.fxml;
    opens com.example.ptp08_2072008.controller;
    exports com.example.ptp08_2072008.controller to javafx.fxml;
}