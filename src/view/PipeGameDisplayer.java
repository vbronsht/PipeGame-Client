package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PipeGameDisplayer extends Canvas
{

    int[][] PipeGameBoard={
            {1,1,1,1},
            {1,1,1,1},
            {1,1,1,1},
            {1,1,1,1},
    };

    private StringProperty wallFileName;
    public String getWallFileName()
    {
        return wallFileName.get();
    }
    public StringProperty wallFileNameProperty()
    {
        return wallFileName;
    }
    public void setWallFileName(String wallFileName)
    {
        this.wallFileName.set(wallFileName);
    }


    private StringProperty L;
    public String getL() {
        return L.get();
    }
    public StringProperty lProperty() {
        return L;
    }
    public void setL(String l) {
        this.L.set(l);
    }


    public PipeGameDisplayer()
    {
        wallFileName = new SimpleStringProperty();
        L = new SimpleStringProperty();

    }

    public void setPipeData() {
        reDraw();
    }

    public void reDraw()
    {
        double W = getWidth();
        double H = getHeight();
        double w = W/PipeGameBoard[0].length;
        double h = W/PipeGameBoard.length;
        Image wall=null;
        try {
            wall = new Image(new FileInputStream(wallFileName.get()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image LPipe=null;
        try {
            LPipe = new Image(new FileInputStream(L.get()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GraphicsContext gc=getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, W/5, H/5);
        gc.drawImage(wall,0,0,W,H);
        gc.drawImage(LPipe,0,0,w,h);

    }

}
