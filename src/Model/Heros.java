package Model;
public class Heros extends Entite  {

    public Heros(Jeu jeu, Case c){
        super(jeu, c);
        this.x = c.x;
        this.y = c.y;
    }

    @Override
    public boolean seDeplacerVers(Case cCible, Direction d){
        if(cCible != null){
        if(cCible.entrer(this, d, cCible)){
            if(cCible.getEntite() != null){
                if(cCible.getEntite().pousser(d) == false){
                    return false;
                }
            }
            c.quitterEntite(cCible, d, this);
            if(cCible.glissant()){
                return this.glisser(cCible, d);
            }
            this.avancer(cCible, d);          
            return true;
        }  
    }
        return true;
    }
    

}