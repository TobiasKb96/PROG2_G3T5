module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires com.h2database;
    requires java.sql;

    requires com.jfoenix;
    requires okhttp3;
    requires com.google.gson;

    opens at.ac.fhcampuswien.fhmdb.models to ormlite.jdbc, com.google.gson;
    exports at.ac.fhcampuswien.fhmdb.controller;
    opens at.ac.fhcampuswien.fhmdb.controller to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb;
    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
}