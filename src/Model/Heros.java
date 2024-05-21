package Model;
public class Heros extends Entite  {

    public Heros(Jeu jeu, Case c){
        super(jeu, c);
        this.x = c.x;
        this.y = c.y;
    }

    @Override
    public boolean seDeplacerVers(Case cCible, Direction d){
        if(cCible.entrer(this, d, cCible)){
            if(cCible.getEntite() != null){
                if(cCible.getEntite().pousser(d) == false){
                    return false;
                }
            }
            // TODO: gérer quitter la case (fissure devient trou)
            // this.setCase(this.getCase().apresQuitter(this, d, cCible));
            if(cCible.glissant(cCible)){
                return this.glisser(cCible, d);
            }
            this.avancer(cCible, d);
            
            
            return false;
        }  
        return true;
    }
    

}