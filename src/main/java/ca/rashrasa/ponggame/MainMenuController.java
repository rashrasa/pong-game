package ca.rashrasa.ponggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController {
    private GUI gui;

    public void setGUI(GUI gui){
        this.gui = gui;
    }

    @FXML
    protected void onPlayPress(ActionEvent e) {
        this.gui.onPlayPress(e);
    }

    @FXML
    protected void onOptionsPress(ActionEvent e) {
        this.gui.onOptionsPress(e);
    }

    @FXML
    protected void onExitPress(ActionEvent e) {
        this.gui.onExitPress(e);
    }

}
