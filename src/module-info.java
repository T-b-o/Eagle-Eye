
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

module Eagle.Eye {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.swt;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    //requires java.sql;
    requires jfoenix;
    requires charm.glisten;
    requires sqlite.jdbc;
    requires java.sql;
    //requires jfxrt;
    //requires rt;

    requires com.microsoft.sqlserver.jdbc;

    opens EagleEyeMaster;
    opens EagleEyeAdmin;
    opens EagleEyeDatabase;
    opens EagleEyeMap;
}