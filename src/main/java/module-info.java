module com.solovev.usersfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires codemodel;
    requires jsonschema2pojo.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.solovev.usersfx to javafx.fxml;
    exports com.solovev.usersfx;
    exports com.solovev.usersfx.model;
    exports com.solovev.usersfx.controllers;
    opens com.solovev.usersfx.controllers to javafx.fxml;
}