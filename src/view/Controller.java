package view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import Controller.MyController;
import model.ClientServerHandler;

//import static com.sun.corba.se.impl.util.Utility.printStackTrace;


public class Controller extends Observable implements Initializable
{
    MyController controller;
    public String end = "";
    public int moves=0;
    public ArrayList<String> PipeGameBoard = new ArrayList<String>();
    private PlaySound sound;
    private String connection = "disconnected";
    @FXML
    PipeGameDisplayer pipeGame ;

    public boolean checkMaze = false;
    
    
    @FXML
    void aboutPage(ActionEvent event){
        popWindow("About");
    }

    @FXML
    void Try(ActionEvent event)  //Should work after server connection
    {
        popWindow("fail");
    }
    
    @FXML
    void Win(ActionEvent event)  //Should work after server connection
    {
        if(moves==0)
            popWindow("fail");
        else
            popWindow("Win");
    }

    @FXML
    void Fail(ActionEvent event)  //Should work after server connection
    {
        popWindow("fail");
    }
    @FXML
    Label time;

    @FXML
    Label madeMoves;

    @FXML
    PipeGameDisplayer PipeDisplayer;

    @FXML
    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        pipeGame.setPipeGameBoard(PipeGameBoard);
        sound = new PlaySound();
        this.controller = new MyController(new ClientServerHandler(), this);
        pipeGame.addEventFilter(MouseEvent.MOUSE_CLICKED,(e)->{
            pipeGame.requestFocus();
            int x = (int)((long)e.getY()*(long)pipeGame.getPipeGameBoard().size()/(long)pipeGame.getHeight());
            int y = (int) ((e.getX() * pipeGame.getPipeGameBoard().get(0).length()) / pipeGame.getWidth());
            pipeGame.switchCell(x,y);
            moves++;
            this.madeMoves.setText("Moves: " + moves);

        });
    }

    /*public void setConnect(String connect) {
   //     this.connection = connect;
        UpdateConnection();
    }*/
    
  /*  void UpdateConnection() {
        this._connect.setText(String.format("Connection Status: %s", connection));
    }*/

    public PipeGameDisplayer getMazeDisplayer() {
        return PipeDisplayer;
    }

    public void connect() {
        setChanged();
        notifyObservers("Connect");
        System.out.println("im in connect");
    }
    
    public void solveGame(){
   //     System.out.println(_connect.getText());
   //     if(_connect.getText().equals("Connection Status: We connected")){
            setChanged();
            notifyObservers("Solve");
   //     }
    }


    public void getFromModel(boolean check){
        this.checkMaze = check;
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
        timer();

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

    public void timer() {
        long start = System.currentTimeMillis();
        Label timeLabel = this.time;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long n = System.currentTimeMillis();
                long millis = ((n - start));

                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                timeLabel.setText("Time: " + hms);
            }
        };
        timer.start();
    }


    public void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./resources"));
        File file = fileChooser.showSaveDialog(null);

        try {
            PrintWriter write = new PrintWriter(file);
            for(int i =0;i<PipeGameBoard.size();i++){
                write.println(PipeGameBoard.get(i));

            }
            write.flush();
            write.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }

}
