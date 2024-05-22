package Model;

public class Fissure extends Case {

    public Fissure(int x, int y){
        super(x, y);
    }

    /**
     * Permet à une entité de rentrer dans la case
     * @param e l'entité qui veut entrer
     * @param d la direction dans laquelle l'entité veut entrer
     * @param c la case dans laquelle l'entité veut entrer
     * @return true si l'entité peut entrer dans la case, false sinon
     */
    public boolean entrer(Entite e, Direction d, Case c) {
        return true;
    }

    public void quitterEntite(Case cCible, Direction d, Entite e){
        
        if(cCible.entrer(e, d , cCible)){
            if(cCible.getEntite() == null){
                this.setEntite(null);
                cCible.setEntite(e);
                e.setCase(cCible);
            }
        }
        e.jeu.addCase(new Trou(this.x, this.y), this.x, this.y);
    }
}