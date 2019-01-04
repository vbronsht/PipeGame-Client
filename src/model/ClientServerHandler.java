package model;

import Controller.MyController;
import view.PipeGameDisplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public class ClientServerHandler extends Observable {
    public ClientServerHandler controller;
    private Socket socket = null;

    public void connectToServer(String ip, int port) throws IOException {

        System.out.println("Connecting to server '" + ip );
        socket = new Socket(ip, port);
        if(socket.isClosed())
        {
            throw new IOException("Error: No such server");
        }
        setChanged();
        notifyObservers("update");
    }



}
