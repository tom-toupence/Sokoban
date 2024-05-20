package Vue_controlleur;

import Model.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class MF extends JFrame implements Observer {
    JPanel[][] tabC;    
    ImageIcon Mur, Vide, Heros, Bloc, Arrivee, Favicon;
    public Jeu jeu;
    public String level;
    public boolean initialized = false;

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
        this.setSize(jeu.SIZE_X*64, jeu.SIZE_Y*64);
        this.setLocation(400, 50);
        this.setIconImage(Favicon.getImage()) ;

        JPanel jp = new JPanel(new BorderLayout());
        JPanel jpC = new JPanel(new GridLayout(jeu.SIZE_X, jeu.SIZE_Y));
        jp.add(jpC, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton quitButton = new JButton("Quitter");
        JButton retryButton = new JButton("Réessayer");
        buttonPanel.add(retryButton);
        buttonPanel.add(quitButton);
        jp.add(buttonPanel, BorderLayout.SOUTH);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearComponents();
                initialized = jeu.InitialisationNiveau(MF.this);
                if (initialized){
                    System.out.println("Le jeu devrait être réinitialisé maintenant.");
                    MF.this.build();
                }else{
                    System.out.println("Erreur lors de la réinitialisation du jeu.");
                }
                ;
            }
        });

        add(jp);


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
                    if (jeu.tab[x][y].getEntite() != null) {
                        tabC[x][y].add(new JLabel(Bloc), BorderLayout.CENTER);
                    }
                } else if (jeu.tab[x][y] instanceof Arrivee) {
                    tabC[x][y].add(new JLabel(Arrivee), BorderLayout.CENTER);
                }
            }
        }
        Point positionHeros = jeu.h.getPosition();
        if (positionHeros != null) {
            tabC[positionHeros.x][positionHeros.y].add(new JLabel(Heros), BorderLayout.CENTER);
        }
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
                }
            }
        }

        
        Point positionHeros = jeu.h.getPosition();
        if (positionHeros != null) {
            tabC[positionHeros.x][positionHeros.y].add(new JLabel(Heros), 0);
        }


        
        Point positionBloc = jeu.b.getPosition();
        if (positionBloc != null) {
            JLabel blocLabel = new JLabel(Bloc, SwingConstants.CENTER);
            tabC[positionBloc.x][positionBloc.y].add(blocLabel,0);
            if (jeu.tab[positionBloc.x][positionBloc.y] instanceof Arrivee) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Bravo vous avez gagné !","", JOptionPane.INFORMATION_MESSAGE);
                    int response = JOptionPane.showConfirmDialog(this, "Voulez-vous rejouer ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    } else {    
                        dispose();
                        Menu menu = new Menu();
                    }
                });
            }
            revalidate();
            repaint();
        }
    }




    public void clearComponents() {
        for (int i = 0; i < jeu.SIZE_X; i++) {
            for (int j = 0; j < jeu.SIZE_Y; j++) {
                if (tabC[i][j] != null) {
                    tabC[i][j].removeAll();
                }
            }
        }
        revalidate();
        repaint();
    }

    private void loadImages() {
        String path = "src/assets/";
        Mur = new ImageIcon(path+"Mur.png");
        Vide = new ImageIcon(path+"Sol.png");
        Heros = new ImageIcon(path+"Heros.png");
        Bloc = new ImageIcon(path+"Bloc.png");
        Arrivee = new ImageIcon(path+"Arrivee.png");
        Favicon = new ImageIcon(path+"Favicon.png");
    }
    
}