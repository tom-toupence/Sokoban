package Model;

import java.awt.*;
import java.util.Map;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Vue_controlleur.MF;

@SuppressWarnings("deprecation")
public class Jeu extends Observable {
    private Map<Case, Point> map;
    public Heros h;
    public int SIZE_X = 10;
    public int SIZE_Y = 10;
    public Case[][] tab;
    public Bloc[][] tabB;
    public Bloc b;

    public void InitialisationNiveau(MF mf) {

    
        // Réinitialiser les tableaux et les cartes
        tab = new Case[SIZE_X][SIZE_Y];
        map = new java.util.HashMap<>();
        tabB = new Bloc[SIZE_X][SIZE_Y];


        this.addObserver(mf);

        for (int i =0; i<10; i++){
            addCase(new Mur(i,0), i, 0);
            addCase(new Mur(i,9), i, 9);
        }
        for (int i =1; i<10; i++){
            addCase(new Mur(0,i), 0, i);
            addCase(new Mur(9,i), 9, i);
        }

        for (int x = 1; x<9; x++){
            for (int y = 1; y<9; y++){
                addCase(new Vide(x,y),x,y);
            }
        }        

        addCase(new Arrivee(3,3), 3, 3);
        h = new Heros(this, tab[4][4]);
        b = new Bloc(this, tab[6][6]);

        mf.build();
        setChanged();
        notifyObservers();


    } 

    public Case getCible(Entite e, Direction d){
        Case cE = e.getCase();
        
        Point pCible = map.get(cE);
        int x = pCible.x;
        int y = pCible.y;

        switch(d){
            case UP:x--;break;
            case DOWN:x++; break;
            case LEFT: y--; break;
            case RIGHT:y++; break;
        }
        Point p = new Point(x,y);
        if (contenuDansGrille(p)) {
            return tab[p.x][p.y];
        } else {
            return null;
        }
    } 


    // Méthode pour déplacer le héros
    public void deplacerHeros(Direction d){
        Case cCible = getCible(h,d);
        h.seDeplacerVers(cCible,d);
        setChanged();
        notifyObservers();
    }


    // Méthode pour ajouter les cases dans la grille
    private void addCase(Case c, int x, int y){
        tab[x][y] = c;
        map.put(c, new Point(x,y));
    }

    // Méthode pour vérifier si la case est dans la grille
    private boolean contenuDansGrille(Point p){
        return p.x >= 0 && p.x < SIZE_X && p.y >= 0 && p.y < SIZE_Y;
    }
}