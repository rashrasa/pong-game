package ca.rashrasa.ponggame;

import javafx.fxml.FXML;

public class MainMenuController {


    @FXML
    protected void onPlayPress(){
        System.out.println("Pressed: Start button");
    }

    @FXML
    protected void onOptionsPress(){
        System.out.println("Pressed: Options button");
    }

    @FXML
    protected void onExitPress(){
        System.out.println("Pressed: Exit button");
    }

}
