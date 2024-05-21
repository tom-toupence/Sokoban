package Vue_controlleur;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class MF extends JFrame implements Observer {
    JPanel[][] tabC;    
    ImageIcon Mur, Vide, Heros, Bloc, Caisse, Arrivee, Favicon, Fissure, Trou, Glace;
    public Jeu jeu;
    public String level;
    public boolean initialized = false;
    int compteur = 0;

    public MF(String level) {
        tabC = new JPanel[20][20];
        this.level = level;
        loadImages();
        jeu = new Jeu();
        initialized = jeu.InitialisationNiveau(this);
        addEC();
    }

    public void build() {
        this.setTitle("Sokoban");
        this.setSize(jeu.SIZE_X*48, jeu.SIZE_Y*48+50);
        this.setLocation(400,50);
        this.setIconImage(Favicon.getImage()) ;

        JPanel jp = new JPanel(new BorderLayout());
        JPanel jpC = new JPanel(new GridLayout(jeu.SIZE_X, jeu.SIZE_Y));
        jp.add(jpC, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton quitterBouton = new JButton("Quitter");
        JButton menuBouton = new JButton("Menu principal");
        buttonPanel.add(menuBouton);
        buttonPanel.add(quitterBouton);
        jp.add(buttonPanel, BorderLayout.SOUTH);

        quitterBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
            }
        });
        add(jp);

        // Ajout des composants
        for (int i = 0; i < jeu.SIZE_X; i++) {
            for (int j = 0; j < jeu.SIZE_Y; j++) {
                tabC[i][j] = new JPanel(new BorderLayout());
                tabC[i][j].setLayout(new OverlayLayout(tabC[i][j]));
                jpC.add(tabC[i][j]);
            }
        }
        for (int x = 0; x < jeu.SIZE_X; x++) {
            for (int y = 0; y < jeu.SIZE_Y; y++) {
                if (jeu.tab[x][y] instanceof Mur) {
                    tabC[x][y].add(new JLabel(Mur), BorderLayout.CENTER);
                } else if (jeu.tab[x][y] instanceof Vide) {
                    tabC[x][y].add(new JLabel(Vide), BorderLayout.CENTER);
                    if (jeu.tab[x][y].getEntite() != null){                       
                        if (jeu.tab[x][y].getEntite() instanceof Bloc)
                            tabC[x][y].add(new JLabel(Bloc), BorderLayout.CENTER);
                        else if (jeu.tab[x][y].getEntite() instanceof Caisse)
                            tabC[x][y].add(new JLabel(Caisse), BorderLayout.CENTER);
                    }
                } else if (jeu.tab[x][y] instanceof Arrivee) {
                    tabC[x][y].add(new JLabel(Arrivee), BorderLayout.CENTER);
                } else if (jeu.tab[x][y] instanceof Fissure) {
                    tabC[x][y].add(new JLabel(Fissure), BorderLayout.CENTER);
                } else if (jeu.tab[x][y] instanceof Trou) {
                    tabC[x][y].add(new JLabel(Trou), BorderLayout.CENTER);
                } else if (jeu.tab[x][y] instanceof Glace) {
                    tabC[x][y].add(new JLabel(Glace), BorderLayout.CENTER);
            }
        }
        }
        Point positionHeros = jeu.h.getPosition();
        if (positionHeros != null) {
            tabC[positionHeros.x][positionHeros.y].add(new JLabel(Heros), BorderLayout.CENTER);
        }
    }

    /**
     * Ajoute un écouteur de clavier pour les déplacements du héros
     */
    public void addEC() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        jeu.deplacerHeros(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        jeu.deplacerHeros(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        jeu.deplacerHeros(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        jeu.deplacerHeros(Direction.DOWN);
                        break;
                }
            }
        });
        this.setFocusable(true); 
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int x = 0; x < jeu.SIZE_X; x++) {
            for (int y = 0; y < jeu.SIZE_Y; y++) {
                tabC[x][y].removeAll(); 
                if (jeu.tab[x][y] instanceof Mur) {
                    tabC[x][y].add(new JLabel(Mur), BorderLayout.CENTER);
                } else if (jeu.tab[x][y] instanceof Vide) {
                    tabC[x][y].add(new JLabel(Vide), BorderLayout.CENTER);
                } else if (jeu.tab[x][y] instanceof Arrivee) {
                    tabC[x][y].add(new JLabel(Arrivee), BorderLayout.CENTER);
                    if(jeu.tab[x][y].getEntite() instanceof Bloc){
                        compteur++;
                        jeu.tab[x][y] = null;
                    }
                    if(compteur == jeu.nb_blocs){
                        WinCondition();
                    }
                } else if (jeu.tab[x][y] instanceof Fissure) {
                    tabC[x][y].add(new JLabel(Fissure), BorderLayout.CENTER);
                } else if (jeu.tab[x][y] instanceof Trou) {
                    tabC[x][y].add(new JLabel(Trou), BorderLayout.CENTER);
                } else if (jeu.tab[x][y] instanceof Glace) {
                    tabC[x][y].add(new JLabel(Glace), BorderLayout.CENTER);
            }
        }
        }

        // Ajout de l'entité Heros
        Point positionHeros = jeu.h.getPosition();
        if (positionHeros != null) {
            tabC[positionHeros.x][positionHeros.y].add(new JLabel(Heros), 0);
        }

        // Ajout des entités Caisse
        for (Caisse c : jeu.caisses) {
            Point positionCaisse = c.getPosition();
            if (positionCaisse != null) {
                tabC[positionCaisse.x][positionCaisse.y].add(new JLabel(Caisse), 0);
            }
        }
        

        for(Bloc b : jeu.blocs){
            Point positionBloc = b.getPosition();
            if (positionBloc != null) {
                tabC[positionBloc.x][positionBloc.y].add(new JLabel(Bloc), 0);
                revalidate();
                repaint();}
    }

        // // Ajout de l'entité Bloc
        // Point positionBloc = jeu.b.getPosition();
        // if (positionBloc != null) {
        //     JLabel blocLabel = new JLabel(Bloc, SwingConstants.CENTER);
        //     tabC[positionBloc.x][positionBloc.y].add(blocLabel,0);
        //     revalidate();
        //     repaint();
        // }

        }
        public void WinCondition(){
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Bravo, vous avez gagné !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
                    int reponse =JOptionPane.showConfirmDialog(this, "Voulez-vous rejouer ?", "Rejouer", JOptionPane.YES_NO_OPTION);
                    if(reponse == JOptionPane.YES_OPTION){
                        dispose();
                        Menu Relance = new Menu();
                    } else {
                        System.exit(0);
                    }
                });
            
         }


    /**
     * Charge les images du jeu
     */
    private void loadImages() {
        String path = "src/assets/";
        Mur = new ImageIcon(path+"Mur.png");
        Vide = new ImageIcon(path+"Sol.png");
        Heros = new ImageIcon(path+"Heros.png");
        Bloc = new ImageIcon(path+"Bloc.png");
        Caisse = new ImageIcon(path+"Caisse.png");
        Arrivee = new ImageIcon(path+"Arrivee.png");
        Favicon = new ImageIcon(path+"Favicon.png");
        Fissure = new ImageIcon(path+"Fissure.png");
        Trou = new ImageIcon(path+"Trou.png");
        Glace = new ImageIcon(path+"Glace.png");
    }
}