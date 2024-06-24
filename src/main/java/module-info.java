module ca.rashrasa.ponggame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens ca.rashrasa.ponggame to javafx.fxml;
    exports ca.rashrasa.ponggame;
}