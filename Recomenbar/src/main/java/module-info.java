module org.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires org.bytedeco.javacv;
    requires org.bytedeco.opencv;
    requires javafx.swing;

    opens org.example.demo3 to javafx.fxml;
    exports org.example.demo3;
}