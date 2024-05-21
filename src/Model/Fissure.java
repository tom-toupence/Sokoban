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

    /**
     * Le sol se transforme en trou
     * @param e l'entité qui veut quitter
     * @param d la direction dans laquelle l'entité veut quitter
     * @param c la case que l'entité veut quitter
     */
    public boolean quitter(Entite e, Direction d, Case c) {
        return true;
    }

    /**
     * Le sol se transforme en trou
     * @param e
     * @param d
     * @param c
     * @return
     */
    public Case aprèsQuitterCase(Entite e, Direction d, Case c) {
        return new Trou(c.getX(), c.getY());
    }
}