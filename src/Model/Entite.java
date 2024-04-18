package Model;

public class Entite {
    Case c;
    
    public Entite() {
    }

    public void seDeplacerVers(Case c,Direction d){
        Entite e = c.getEntite();
        if(e == null){
            e.pousser(d);
        }
        c.entrer(this, d);
    }

}
