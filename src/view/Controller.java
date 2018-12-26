package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable
{
    Controller controller;
    public String end = "";
    public ArrayList<String> PipeGameBoard = new ArrayList<String>();
    private PlaySound sound;

    @FXML
    PipeGameDisplayer pipeGame ;

    @FXML
    void aboutPage(ActionEvent event){
        popWindow("About");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        pipeGame.setPipeGameBoard(PipeGameBoard);
        sound = new PlaySound();
        pipeGame.addEventFilter(MouseEvent.MOUSE_CLICKED,(e)->{
            pipeGame.requestFocus();
            int x = (int)((long)e.getY()*(long)pipeGame.getPipeGameBoard().size()/(long)pipeGame.getHeight());
            int y = (int) ((e.getX() * pipeGame.getPipeGameBoard().get(0).length()) / pipeGame.getWidth());
            pipeGame.switchCell(x,y);
            //points++;
            /*if(mazeDisplayer.flag==1)
            {
                points--;
            }*/
            //mazeDisplayer.flag=0;
            //this.score.setText("Moves: " + points);

        });
    }

    public void openFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./resources"));
        File board = fileChooser.showOpenDialog(null);
        if (board != null) {
            PipeGameBoard = openBoard(board);
            pipeGame.setPipeGameBoard(PipeGameBoard);
            for (int i = 0; i < PipeGameBoard.size(); i++) {
                if (PipeGameBoard.get(i).length() > end.length()) {
                    end = PipeGameBoard.get(i);
                }
            }
        } else {
            System.out.println("Error: File not found!");

        }

    }

    public ArrayList<String> openBoard(File board) throws IOException {
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(board));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String streader = null;
        ArrayList<String> tempBoard = new ArrayList<>();
        try {
            streader = buffer.readLine();
            while (streader != null) {
                tempBoard.add(streader);
                streader = buffer.readLine();
            }
            return tempBoard;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void theme(ActionEvent event){
        String text = ((MenuItem)event.getSource()).getText();
        System.out.println(text.substring(0,1));
        String temp = text.substring(0,1);
        if (temp.equals("B"))
        {
            pipeGame.setTheme("theme1");
        }
        else
        {
            pipeGame.setTheme("theme2");
        }
    }
    private void popWindow(String fxmlPage){
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""+fxmlPage+".fxml"));
            //Parent root1 = (Parent) fxmlLoader.load();
            Parent root1 = FXMLLoader.load(getClass().getResource("./fxml_files/"+fxmlPage+".fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
