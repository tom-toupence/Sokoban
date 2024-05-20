package Model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

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

    public boolean InitialisationNiveau(MF mf) {
        // Réinitialiser les tableaux et les cartes

        // lire le fichier pour initialiser le niveau
        BufferedReader lecteur = null;
        String ligne;
        try
        {
            lecteur = new BufferedReader(new FileReader("src/assets/Levels/"+mf.level+".txt"));
        } catch(FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture du fichier");
        }
        try {
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
        } catch (IOException e) {
            // TODO MESSAGE ERREUR
            System.out.println("erreur lecture fichier");
            return false;
            //e.printStackTrace();
        } catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("trop sur une ligne");
            return false;
            //e.printStackTrace();
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

        switch(d){
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


    // Méthode pour déplacer le héros
    public void deplacerHeros(Direction d){
        Case cCible = getCible(h,d);
        h.seDeplacerVers(cCible,d);
        setChanged();
        notifyObservers();
    }


    // Méthode pour ajouter les cases dans la grille
    private void addCase(Case c, int x, int y){
        tab[x][y] = c;
        map.put(c, new Point(x,y));
    }

    // Méthode pour vérifier si la case est dans la grille
    private boolean contenuDansGrille(Point p){
        return p.x >= 0 && p.x < SIZE_X && p.y >= 0 && p.y < SIZE_Y;
    }
}