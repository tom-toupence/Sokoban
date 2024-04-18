package Model;

import java.awt.*;
import java.util.Map;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class Jeu extends Observable {
    Map<Case, Point> map;
    Case[][] tab;
    Heros h;
    public void deplacerHeros(Direction d){
        Case cCible = getCible(h,d);
        h.seDeplacerVers(cCible,d);
        setChanged();
        notifyObservers();
    }




    private void addCase(Case e, int x, int y){
        
    }


    private Point calculerPointCible(Point pCourant, Direction d){
        Point pCible = null;

        switch(d){
            case UP: pCible = new Point(pCourant.x, pCourant.y - 1);break;
            case DOWN: pCible = new Point(pCourant.x, pCourant.y + 1);break;
            case LEFT: pCible = new Point(pCourant.x - 1, pCourant.y);break;
            case RIGHT: pCible = new Point(pCourant.x + 1, pCourant.y);break;
        }
        return pCible;
    }

    public boolean deplacerEntite(Entite e, Direction d){
        boolean retour = true;

        Point pCourant = map.get(e.getCase());

        Point pCible = calculerPointCible(pCourant,d);

        if(contenuDansGrille(pCible)){
            Entite eCible = caseALaPosition(pCible).getEntite();
        }
    }

}
