package Model;

public class Glace extends Case {

    public Glace(int x, int y){
        super(x, y);
    }

    public boolean entrer(Entite e, Direction d, Case c) {
        return true;
    
    }
    
    public boolean glissant(Case c){
        return true;
    }
}
