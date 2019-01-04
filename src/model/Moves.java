package model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Moves {
    private int steps;
    private int time;
    private int points;
    
    public int get_moves() {
        return steps;
    }
    @XmlElement
    public void set_moves(int steps) {
        this.steps = steps;
    }
    public int get_timer() {
        return time;
    }
    @XmlElement

    public void set_timer(int timer) {
        this.time = timer;
    }

}
