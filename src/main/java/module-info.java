module com.solovev.usersfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires codemodel;
    requires jsonschema2pojo.core;
    requires com.fasterxml.jackson.annotation;


    opens com.solovev.usersfx to javafx.fxml;
    exports com.solovev.usersfx;
    exports com.solovev.usersfx.controllers;
    opens com.solovev.usersfx.controllers to javafx.fxml;
}