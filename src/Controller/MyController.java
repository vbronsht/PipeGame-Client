package Controller;

import model.ClientServerHandler;
import model.ClientServerHandler;
import view.Controller;
//import view.fxml_files.*;
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
        view.addObserver(this);
        
    }

    @Override
    public void update(Observable observer, Object arg) {
        if(observer == serverhandler){
            if(arg instanceof String){
                if(arg.equals("Connect"))
                {
                    view.notifyObservers();
                }
            }

        }else if(observer == view){
            if(arg instanceof String){
                if(((String)arg).equals("Connect")){
                    try {
                        serverhandler.connectToServer("127.0.0.1",6400);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
