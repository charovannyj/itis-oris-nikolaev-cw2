module net {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires org.json;

    opens com.solncev to javafx.fxml;
    exports com.solncev.fx.chat;
}