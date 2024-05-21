package Model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Observable;
import Vue_controlleur.MF;

@SuppressWarnings("deprecation")
public class Jeu extends Observable {
    private Map<Case, Point> map;
    public Heros h;
    public int SIZE_X;
    public int SIZE_Y;
    public Case[][] tab;
    public Bloc b;
    public Caisse[] caisses = new Caisse[0];
    public Trou[] trous = new Trou[0];

    /**
     * Méthode pour initialiser le niveau
     * @param mf : la fenêtre principale
     * @return true si le niveau est initialisé, false sinon
     */
    public boolean InitialisationNiveau(MF mf) {
        BufferedReader lecteur = null;
        String ligne;
        try {
            lecteur = new BufferedReader(new FileReader("src/assets/Levels/"+mf.level+".txt"));
            // premières lignes : taille de la grille
            SIZE_X = Integer.parseInt(lecteur.readLine());
            SIZE_Y = Integer.parseInt(lecteur.readLine());
            tab = new Case[SIZE_X][SIZE_Y];
            map = new java.util.HashMap<>();
            this.addObserver(mf);

            // autres lignes : contenu de la grille
            int x = 0;
            while ((ligne = lecteur.readLine()) != null) {
                int y = 0;
                for (char c : ligne.toCharArray()) {
                    switch (c) {
                        case 'M':
                            addCase(new Mur(x, y), x, y);
                            break;
                        case 'A':
                            addCase(new Arrivee(x, y), x, y);
                            break;
                        case 'H':
                            addCase(new Vide(x, y), x, y);
                            h = new Heros(this, tab[x][y]);
                            break;
                        case 'B':
                            addCase(new Vide(x, y), x, y);
                            b = new Bloc(this, tab[x][y]);
                            break;
                        case 'C':
                            addCase(new Vide(x, y), x, y);
                            Caisse ca = new Caisse(this, tab[x][y]);
                            caisses = java.util.Arrays.copyOf(caisses, caisses.length+1);
                            caisses[caisses.length-1] = ca;
                            break;
                        case 'T':
                            addCase(new Trou(x, y), x, y);
                            /*
                            Trou tr = new Trou(this, tab[x][y]);
                            trous = java.util.Arrays.copyOf(trous, trous.length+1);
                            trous[trous.length-1] = tr;*/
                            break;
                        case 'F':
                            addCase(new Fissure(x, y), x, y);
                            break;
                        case 'G':
                            addCase(new Glace(x, y), x, y);
                            break;
                        default: // cas vide
                            addCase(new Vide(x, y), x, y);
                            break;
                    }
                    y++;
                }
                x++;
            }
                System.out.println(ligne);
                lecteur.close();
        } catch (Exception e) {
            // erreur de lecture du fichier
            return false;
        }
        mf.build();
        setChanged();
        notifyObservers();
        return true;
    } 

    public Case getCible(Entite e, Direction d){
        Case cE = e.getCase();
        Point pCible = map.get(cE);
        int x = pCible.x;
        int y = pCible.y;

        switch(d) {
            case UP:x--;break;
            case DOWN:x++; break;
            case LEFT: y--; break;
            case RIGHT:y++; break;
        }
        Point p = new Point(x,y);
        if (contenuDansGrille(p)) {
            return tab[p.x][p.y];
        } else {
            return null;
        }
    } 
    public void deplacerHeros(Direction d){
        Case cCible = getCible(h,d);
        h.seDeplacerVers(cCible,d);
        setChanged();
        notifyObservers();
    }

    /**
     * Méthode pour ajouter une case à la grille
     * @param c : la case à ajouter
     * @param x : la position x
     * @param y : la position y
     */
    private void addCase(Case c, int x, int y){
        tab[x][y] = c;
        map.put(c, new Point(x,y));
    }

    /**
     * Méthode pour vérifier si un point est dans la grille
     * @param p : le point à vérifier
     * @return true si le point est dans la grille, false sinon
     */
    private boolean contenuDansGrille(Point p){
        return p.x >= 0 && p.x < SIZE_X && p.y >= 0 && p.y < SIZE_Y;
    }
}