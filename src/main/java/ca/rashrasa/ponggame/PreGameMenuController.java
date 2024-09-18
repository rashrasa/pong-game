package ca.rashrasa.ponggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class PreGameMenuController {
    private GUI gui;

    @FXML
    TextField max_score_field;

    @FXML
    Slider bot_difficulty_slider;

    public void setGUI(GUI gui) {
        this.gui=gui;
    }

    @FXML
    protected void onStartPress(ActionEvent e) {
        this.gui.onStartPress(e, Integer.parseInt(this.max_score_field.getText()), (int)(this.bot_difficulty_slider.getValue()));
    }
}
