package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable
{

    @FXML
    PipeGameDisplayer pipeGame ;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        pipeGame.reDraw();
    }
}
