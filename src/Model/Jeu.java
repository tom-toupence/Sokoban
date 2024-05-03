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
    public int SIZE_X = 20;
    public int SIZE_Y = 20;
    public Case[][] tab;
    private MF mf;
    public Bloc[][] tabB;
    public Bloc b;

    public void InitialisationNiveau(MF mf) {
        // murs
        // TODO: il avait mis "new Mur(this)" dans l'initialisation de ses murs...
        tab = new Case[SIZE_X][SIZE_Y];
        map = new java.util.HashMap<>();
        
        this.addObserver(mf);

        for (int i =0; i<20; i++){
            addCase(new Mur(i,0), i, 0);
            addCase(new Mur(i,19), i, 19);
        }
        for (int i =1; i<20; i++){
            addCase(new Mur(0,i), 0, i);
            addCase(new Mur(19,i), 19, i);
        }

        for (int x = 1; x<19; x++){
            for (int y = 1; y<19; y++){
                addCase(new Vide(x,y),x,y);
            }
        }

        h = new Heros(this, tab[4][4]);
        b = new Bloc(this, tab[6][6]); 

        tabB = new Bloc[SIZE_X][SIZE_Y];
        tabB[6][6] = b;

        

        mf.build();
        setChanged();
        notifyObservers();


    }
 


    private Case getCible(Heros h, Direction d){
        Point positionHeros = h.getPosition();
    
        int x = positionHeros.x;
        int y = positionHeros.y;
        
        
        switch(d){
            case UP:x--;break;
            case DOWN: x++; break;
            case LEFT: y--; break;
            case RIGHT: y++; break;
        }

    
        Point pCible = new Point(x, y);
        if (contenuDansGrille(pCible)) {
            return tab[x][y];
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
    private void addCase(Case e, int x, int y){
        tab[x][y] = e;
        map.put(e, new Point(x,y));
    }

    
    // Méthode pour calculer la case cible
    public Case calculerPointCible(Case c, Direction d){
        Point pCible = null;

        switch(d){
            case UP: pCible = new Point(c.x-1, c.y);break;
            case DOWN: pCible = new Point(c.x+1, c.y);break;
            case LEFT: pCible = new Point(c.x, c.y -1);break;
            case RIGHT: pCible = new Point(c.x, c.y+1);break;
        }
        if (contenuDansGrille(pCible)){
            return tab[pCible.x][pCible.y];
        } else {
            return null;
        }
    }



    // Méthode pour déplacer une entité
    /* public boolean deplacerEntite(Entite e, Direction d){
        boolean retour = true;

        Point pCourant = map.get(e.getCase());

        Point pCible = calculerPointCible(e.getCase(),d);

        if(contenuDansGrille(pCible)){
            Entite eCible = caseALaPosition(pCible).getEntite();
        }

        // TODO : rectifier le return

        return retour;
    } */



    /**
     * Envoie un booléen indiquant si la case est dans la grille.
     * @param p
     * @return
     */
    private boolean contenuDansGrille(Point p){
        return p.x >= 0 && p.x < SIZE_X && p.y >= 0 && p.y < SIZE_Y;
    }


    /**
     * Renvoie la case cible si le déplacement est possible, null sinon.
     * @param p
     * @return
     */
    private Case caseALaPosition(Point p){
        Case retour = null;
        if (contenuDansGrille(p)){
            retour = tab[p.x][p.y];
        }
        return retour;
    }
}