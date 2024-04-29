package Model;

public class Vide extends Case {

    public Vide(int x, int y){
        super(x, y);
    } 

    public boolean entrer(Entite e, Direction d) {
        if(e == null){
            e.avancerDirectionChoisie(d);
            return true;
        }
        else{
            return false;
        }
    }
}
