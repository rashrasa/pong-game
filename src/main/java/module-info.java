module ca.rashrasa.ponggame {
    requires javafx.controls;
    requires javafx.fxml;

    opens ca.rashrasa.ponggame to javafx.fxml;
    exports ca.rashrasa.ponggame;
}