    package Vue_controlleur;
    import Model.*;
    import javax.swing.*;
    import javax.swing.border.EmptyBorder;
    import java.awt.*;
    import java.awt.event.*;

    public class Menu extends JFrame{

        public Menu() {

            this.setTitle("Sokoban");
            this.setSize(750, 750);
            this.setLocation(400, 50);
            this.setIconImage(new ImageIcon("src/assets/favicon.png").getImage());

            JPanel panel = new JPanel(new BorderLayout());

            // Panneau fond
            JLabel background = new JLabel(new ImageIcon("src/assets/fond.jpg"));
            JLabel titleLabel = new JLabel("SOKOBAN", SwingConstants.CENTER);
            titleLabel.setFont(new Font("COMIC SANS MS", Font.BOLD, 48));
            titleLabel.setForeground(Color.BLACK); 
            titleLabel.setBounds(0, 75, 750, 100);
            background.add(titleLabel, BorderLayout.CENTER);
            panel.add(background, BorderLayout.CENTER);

            
            // Panneau de bouttons
            JPanel buttonPanel = new JPanel();
            JButton playButton = new JButton("Jouer");
            JButton quitButton = new JButton("Quitter");
            buttonPanel.add(playButton);
            buttonPanel.add(quitButton);
            buttonPanel.setOpaque(false);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            // Actions des boutons
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MF mf = new MF(1); // CHANGE NUMBER
                    mf.setVisible(true);
                    setVisible(false);
                }
            });

            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            this.setContentPane(panel);
            this.setVisible(true);
        }}