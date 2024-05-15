    package Vue_controlleur;

    import Model.*;
    import javax.swing.*;
    import javax.swing.border.Border;
    import java.awt.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.util.Observable;
    import java.util.Observer;
    import java.util.Random;

    public class MF extends JFrame implements Observer {
        JPanel[][] tabC;

        public Jeu jeu;
        public MF() {
            tabC = new JPanel[20][20];
            jeu = new Jeu();
            jeu.InitialisationNiveau(this);
            addEC();
        }

        public void build() {
            JPanel jp = new JPanel(new BorderLayout());
            JPanel jpC = new JPanel(new GridLayout(jeu.SIZE_X, jeu.SIZE_Y));
            this.setLocation(400, 50); // Centrer la fenêtre
            setSize(750, 750);
            // JPanel jpInfo = ....
            jp.add(jpC, BorderLayout.CENTER);
            // jp.add(jpInfo, BorderLayout.EAST);
            Border blackline = BorderFactory.createLineBorder(Color.BLACK);
            add(jp);
            for (int i = 0; i < jeu.SIZE_X; i++) {
                for (int j = 0; j < jeu.SIZE_Y; j++) {
                    tabC[i][j] = new JPanel();
                    tabC[i][j].setBorder(blackline);
                    jpC.add(tabC[i][j]);
                }
            }

            // Parcourir le tableau tab et mettre à jour les couleurs en fonction du type de case
            for (int x = 0; x < jeu.SIZE_X; x++) {
                for (int y = 0; y < jeu.SIZE_Y; y++) {
                    if (jeu.tab[x][y] instanceof Mur) {
                        tabC[x][y].setBackground(Color.GRAY);
                    } else if (jeu.tab[x][y] instanceof Vide) {
                        tabC[x][y].setBackground(Color.WHITE);
                        if (jeu.tabB[x][y] != null) {
                            tabC[x][y].setBackground(Color.RED);
                        }
                    }
                    Point positionHeros = jeu.h.getPosition();
                    if (positionHeros != null) {
                        tabC[positionHeros.x][positionHeros.y].setBackground(Color.BLUE);
                    }   
                                
                }
            }

            jp.setBorder(blackline);
        }

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
            requestFocus();
        }

        @Override
        public void update(Observable o, Object arg) {
            for (int x = 0; x < jeu.SIZE_X; x++) {
                for (int y = 0; y < jeu.SIZE_Y; y++) {
                    if (jeu.tab[x][y] instanceof Mur) {
                        tabC[x][y].setBackground(Color.GRAY);
                    } else if (jeu.tab[x][y] instanceof Vide) {
                        tabC[x][y].setBackground(Color.WHITE);
                    } else if (jeu.tab[x][y] instanceof Arrivee) {
                        tabC[x][y].setBackground(Color.GREEN);
                    }
                }
            }
            
            // Mettre à jour la position du héros
            Point positionHeros = jeu.h.getPosition();
            if (positionHeros != null) {
                tabC[positionHeros.x][positionHeros.y].setBackground(Color.BLUE);
            }

            Point positionBloc = jeu.b.getPosition();
            if (positionBloc != null) {
                tabC[positionBloc.x][positionBloc.y].setBackground(Color.RED);
            }
        }
    }

