package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
@SuppressWarnings("deprecation")
public class Heros extends Entite  {

    public int y;
    public int x;
    private List<Observer> observers = new ArrayList<>();


    public Heros(int x, int y){
        this.x = x;
        this.y = y;
    }


    public void seDeplacer(Direction j) {
        switch (j) {
            case UP:
                if (this.y > 0) {
                    this.y--;
                    break;
                } else {
                    break;
                }
            case DOWN:
                if (this.y < 9) {
                    this.y++;
                    break;
                } else {
                    break;
                }
            case LEFT:
                if (this.x > 0) {
                    this.x--;
                    break;
                } else {
                    break;
                }
            case RIGHT:
                if (this.x < 9) {
                    this.x++;
                    break;
                } else {
                    break;
                }
        }
        setChanged();
        notifyObservers();
    }
}
