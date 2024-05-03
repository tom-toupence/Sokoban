package Model;

import java.util.Observable;


public class Entite extends Observable {


    Case c;

    public Entite(Jeu _jeu, Case _c) {
        //super(_jeu);
        c = _c;
        c.setEntite(this);

    }

    public boolean seDeplacerVers(Case c,Direction d){
        return true;
        
        /* Entite e = c.getEntite();
        if(e!=null){
            e.pousser(d);
        }*/
    }

    public void quitterCase(){
        c=null;
    }

    public void setCase(Case _c){
        c = _c;
    }

    public Case getCase(){
        return c;
    }

    public void allerSurCase(Case _c){
        c = _c;
    }

    public boolean pousser(Direction d){
        return false;
    }

    public void avancerDirectionChoisie(Case c,Direction d){
        return;
    }
    

}
