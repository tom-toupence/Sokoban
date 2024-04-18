package Model;

import Vue_controlleur.MF;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Case extends Observable {

    // TODO: re-définir les égalités (pour que la hashmap fonctionne)

    public int x;
    public int y;
    private List<Observer> observers = new ArrayList<>();

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Direction j) {
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