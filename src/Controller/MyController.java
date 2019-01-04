package Controller;

import model.ClientServerHandler;
import model.ClientServerHandler;
import view.Controller;
import view.fxml_files.*;
import view.PipeGameDisplayer;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MyController implements Observer {
    ClientServerHandler serverhandler;
    Controller view;

    public MyController(ClientServerHandler serverhandler, Controller view){
        this.serverhandler = serverhandler;
        this.view = view;
        serverhandler.addObserver(this);
        
    }

    @Override
    public void update(Observable o, Object arg) {
    	System.out.println("imin update");
        if(o == serverhandler){
            if(arg instanceof String){
                if(arg.equals("Connect")){
                    view.notifyObservers();
                }else if(arg.equals("update")) {
                    view.setConnect("We connected");
                }else if(arg.equals("Error")){
                    view.setConnect("disconnected");
                }
            }

        }else if(o == view){
            // send level sol request to serverhandlers
            if(arg instanceof String){
                if(((String)arg).equals("Connect")){
                    try {
                        serverhandler.connectToServer(view.getIp(),Integer.parseInt(view.getPort()));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(((String)arg).equals("Solve")){

                        PipeGameDisplayer board = view.getMazeDisplayer();
                    try {
                        serverhandler.Solver(board);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else if(arg.equals("check")){
                    try {
                        boolean checkMaze = serverhandler.PipeTestSolution(view.getMazeDisplayer());
                        view.getFromModel(checkMaze);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
