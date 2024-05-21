package Model;

public class Glace extends Case {

    public Glace(int x, int y){
        super(x, y);
    }

    public boolean entrer(Entite e, Direction d, Case c) {
        return true;
    
    }

    public void quitterEntite(Case cCible, Entite e){
        this.setEntite(null);
        cCible.setEntite(e);
        e.setCase(cCible);
    }

    public boolean glisser(Case c){
        return true;
    }
}
