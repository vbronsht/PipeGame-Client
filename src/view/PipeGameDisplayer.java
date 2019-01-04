package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import view.Controller;

public class PipeGameDisplayer extends Canvas
{

    private String end = "";
    private String themeName ="theme1";
    private Controller temp;
    private ArrayList<String> PipeGameBoard;
    

    public ArrayList<String> getPipeGameBoard() {
        return PipeGameBoard;
    }


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


    private StringProperty l;
    public String getL() {
        return l.get();
    }
    public StringProperty lProperty() {
        return l;
    }
    public void setL(String l) {
        this.l.set(l);
    }

    private StringProperty f;
    public String getF() {
        return f.get();
    }
    public StringProperty fProperty() {
        return f;
    }
    public void setF(String f) {
        this.f.set(f);
    }

    private StringProperty Seven;
    public String getSeven() {
        return Seven.get();
    }
    public StringProperty sevenProperty() {
        return Seven;
    }
    public void setSeven(String seven) {
        this.Seven.set(seven);
    }

    private StringProperty j;
    public String getJ() {
        return j.get();
    }
    public StringProperty jProperty() {
        return j;
    }
    public void setJ(String j) {
        this.j.set(j);
    }

    private StringProperty Horizontal;
    public String getHorizontal() {
        return Horizontal.get();
    }
    public StringProperty horizontalProperty() {
        return Horizontal;
    }
    public void setHorizontal(String horizontal) {
        this.Horizontal.set(horizontal);
    }

    private StringProperty Vertical;
    public String getVertical() {
        return Vertical.get();
    }
    public StringProperty verticalProperty() {
        return Vertical;
    }
    public void setVertical(String vertical) {
        this.Vertical.set(vertical);
    }

    private StringProperty start;
    public String getStart() {
        return start.get();
    }
    public StringProperty startProperty() {
        return start;
    }
    public void setStart(String start) {
        this.start.set(start);
    }

    private StringProperty finish;
    public String getFinish() {
        return finish.get();
    }
    public StringProperty finishProperty() {
        return finish;
    }
    public void setFinish(String finish) {
        this.finish.set(finish);
    }


    public PipeGameDisplayer()
    {
        wallFileName = new SimpleStringProperty();
        l = new SimpleStringProperty();
        f = new SimpleStringProperty();
        Seven = new SimpleStringProperty();
        j = new SimpleStringProperty();
        Horizontal = new SimpleStringProperty();
        Vertical = new SimpleStringProperty();
        start = new SimpleStringProperty();
        finish = new SimpleStringProperty();
  //      setPipeGameBoard(temp.openBoard(new File("./resources/initial.txt")));
        reDraw();
    }


    public void setPipeData(ArrayList<String> Data) {
        PipeGameBoard = Data;
        reDraw();
    }

    public void reDraw() {
        if (PipeGameBoard != null) {
            for (int i = 0; i < PipeGameBoard.size(); i++) {
                if (PipeGameBoard.get(i).length() > end.length()) {
                    end = PipeGameBoard.get(i);
                }
            }
            double W = getWidth();
            double H = getHeight();
            double w = W / end.length();
            double h = H / PipeGameBoard.size();
            char pipe;
            Image wall = null;
            try {
                wall = new Image(new FileInputStream(wallFileName.get()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image LPipe = null;
            try {
                LPipe = new Image(new FileInputStream("./resources/"+themeName+"/L.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image FPipe = null;
            try {
                FPipe = new Image(new FileInputStream("./resources/"+themeName+"/F.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image sevenPipe = null;
            try {
                sevenPipe = new Image(new FileInputStream("./resources/"+themeName+"/7.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image JPipe = null;
            try {
                JPipe = new Image(new FileInputStream("./resources/"+themeName+"/J.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image horizontalPipe = null;
            try {
                horizontalPipe = new Image(new FileInputStream("./resources/"+themeName+"/pipeline.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image verticalPipe = null;
            try {
                verticalPipe = new Image(new FileInputStream("./resources/"+themeName+"/minus.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image startPipe = null;
            try {
                startPipe = new Image(new FileInputStream("./resources/"+themeName+"/start.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image finishPipe = null;
            try {
                finishPipe = new Image(new FileInputStream("./resources/"+themeName+"/finish.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            GraphicsContext gc = getGraphicsContext2D();
            for (int i = 0; i < PipeGameBoard.size(); i++)
            {
                for (int j = 0; j < PipeGameBoard.get(i).length(); j++)
                {
                    pipe = PipeGameBoard.get(i).charAt(j);
                    if (pipe == '|')
                    {
                        gc.clearRect(j * w, i * h, w, h);
                        gc.drawImage(horizontalPipe, j * w, i * h, w, h);
                    }
                    if (pipe == '-')
                    {
                        gc.clearRect(j * w, i * h, w, h);
                        gc.drawImage(verticalPipe, j * w, i * h, w, h);
                    }
                    if (pipe == 'L')
                    {
                        gc.clearRect(j * w, i * h, w, h);
                        gc.drawImage(LPipe, j * w, i * h, w, h);
                    }
                    if (pipe == '7')
                    {
                        gc.clearRect(j * w, i * h, w, h);
                        gc.drawImage(sevenPipe, j * w, i * h, w, h);
                    }
                    if (pipe == 'J')
                    {
                        gc.clearRect(j * w, i * h, w, h);
                        gc.drawImage(JPipe, j * w, i * h, w, h);
                    }
                    if (pipe == 'F')
                    {
                        gc.clearRect(j * w, i * h, w, h);
                        gc.drawImage(FPipe, j * w, i * h, w, h);
                    }
                    if (pipe == 's')
                    {
                        gc.clearRect(j * w, i * h, w, h);
                        gc.drawImage(startPipe, j * w, i * h, w, h);
                    }
                    if (pipe == 'g')
                    {
                        gc.clearRect(j * w, i * h, w, h);
                        gc.drawImage(finishPipe, j * w, i * h, w, h);
                    }
                }
            }
            //gc.setFill(Color.RED);
            //gc.fillRect(0, 0, W / 5, H / 5);
            //gc.drawImage(wall, 0, 0, W, H);
            //gc.drawImage(FPipe, 0, 0, w, h);

        }
    }


    public void setPipeGameBoard(ArrayList<String> pipeGameBoard) {
        PipeGameBoard = pipeGameBoard;
        reDraw();
    }

   public void switchCell(int x, int y) { //x to right, y to down
        String fix;
        char letter = this.PipeGameBoard.get(x).charAt(y);
            switch (letter) {
                case '-': {
                    fix = lineChanger(x, y, '|');
                    PipeGameBoard.set(x, fix);
                    break;
                }
                case '|': {
                    fix = lineChanger(x, y, '-');
                    PipeGameBoard.set(x, fix);
                    break;
                }
                case '7': {
                    fix = lineChanger(x, y, 'J');
                    PipeGameBoard.set(x, fix);
                    break;
                }
                case 'J': {
                    fix = lineChanger(x, y, 'L');
                    PipeGameBoard.set(x, fix);
                    break;
                }
                case 'L': {
                    fix = lineChanger(x, y, 'F');
                    PipeGameBoard.set(x, fix);
                    break;
                }
                case 'F': {
                    fix = lineChanger(x, y, '7');
                    PipeGameBoard.set(x, fix);
                    break;
                }

            }
            this.reDraw();
    }

    //Switch the character in the line
    private String lineChanger(int i,int j,char letter){
        StringBuilder temp = new StringBuilder(PipeGameBoard.get(i));
        temp.setCharAt(j, letter);
        return temp.toString();
    }

    public void setTheme(String theme) {
        this.themeName = theme;
        reDraw();
    }



}
