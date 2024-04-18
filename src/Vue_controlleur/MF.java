package Vue_controlleur;

import Model.Case;
import Model.Direction;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class MF extends JFrame implements Observer {
    int L = 10;
    int H = 10;
    JPanel[][] tabC;
    private Random random;
    private Case c;

    public MF(Case c) {
        this.c = c;
        random = new Random();
        tabC = new JPanel[L][H];
        build();
        addEC();
    }

    public void build() {
        JPanel jp = new JPanel(new BorderLayout());
        JPanel jpC = new JPanel(new GridLayout(L, H));
        this.setLocation(400, 50); // Centrer la fenêtre
        setSize(750, 750);
        // JPanel jpInfo = ....
        jp.add(jpC, BorderLayout.CENTER);
        // jp.add(jpInfo, BorderLayout.EAST);
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        add(jp);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < H; j++) {
                tabC[i][j] = new JPanel();
                tabC[i][j].setBackground(Color.WHITE);
                tabC[i][j].setBorder(blackline);
                jpC.add(tabC[i][j]);
            }
        }
        tabC[c.x][c.y].setBackground(Color.black);
        jp.setBorder(blackline);

    }

    public void addEC() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        c.move(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        c.move(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        c.move(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        c.move(Direction.DOWN);
                        break;
                }
            }
        });
        requestFocus();
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                tabC[i][j].setBackground(Color.WHITE);
            }
        }
        tabC[c.y][c.x].setBackground(Color.black);
    }
}


