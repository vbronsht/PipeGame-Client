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

    
    //connect to server with port6400
    public void connectToServer(String ip, int port) throws IOException {

        System.out.println("Connecting to server '" + ip );
        socket = new Socket(ip, port);
        if(socket.isClosed())
        {
            throw new IOException("server is close");
        }
        setChanged();
        notifyObservers("update");

    }
    public boolean PipeTestSolution(PipeGameDisplayer board) throws IOException {
    	PipeGameDisplayer tBoard = new PipeGameDisplayer();
        tBoard.setPipeData(board.getPipeGameBoard());
        this.Solver(tBoard);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tBoard.getPipeGameBoard() == board.getPipeGameBoard();
    }
    
    //solve a pipegame maze
    public void Solver(PipeGameDisplayer board) throws IOException {
        if(socket == null) {
            System.err.println("never to be printed!!!!");
            throw new IOException("no connection established");
        }
        if(socket.isClosed()) {
            throw new IOException("server is close");
        }
        if(board.getPipeGameBoard().isEmpty()){
            setChanged();
            notifyObservers("Error");
            throw new IOException("board is empty");

        }
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ArrayList<String> data = board.getPipeGameBoard();


            for(String l : data) {
                out.println(l);
            }
            out.println("done");
            out.flush();

            new Thread(()->{
                while(!Thread.currentThread().isInterrupted()) {
                    try {
                        String _line;
                        while (!(_line = in.readLine()).equals("done")) {
                            int i2 = Integer.parseInt(_line.split(",")[0]);
                            int j = Integer.parseInt(_line.split(",")[1]);
                            int times = Integer.parseInt(_line.split(",")[2]);
                            board.switchCell(i2, j);
                            Thread.sleep(100);
                        }
                        Thread.currentThread().interrupt();
                    }catch(InterruptedException | IOException ignored){}
                }
            }).start();

        }
        catch (IOException ignored) {
        }

        // when we the sol from sever we notify to our observer.
        this.notifyObservers("solution....");
    }

    public void DisconnectFromServer() throws IOException{
        if(socket.isClosed()) {
            throw new IOException("server is close");
        }
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        in.close();
        out.close();
        socket.close();
    }



}
