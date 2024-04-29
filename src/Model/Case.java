package Model;

import Vue_controlleur.MF;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
@SuppressWarnings("deprecation")

public class Case extends Observable {
    public int y;
    public int x;
    private List<Observer> observers = new ArrayList<>();

    Entite entite;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void entrer(Entite e) {

    }

    public Entite getEntite(){
        return entite;
    }

}