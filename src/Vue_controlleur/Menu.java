    package Vue_controlleur;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
import java.io.File;

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

        // Panneau de boutons
        JPanel buttonPanel = new JPanel();
        // récupération niveaux disponibles
        File levelFolder = new File("src/assets/Levels");
        File[] leverlList = levelFolder.listFiles();
        String[] levels = new String[leverlList.length];
        for (int i = 0; i < leverlList.length; i++) {
            levels[i] = leverlList[i].getName().replace(".txt", "");
        }
        JComboBox<String> levelList = new JComboBox<>(levels);
        levelList.setSelectedIndex(0);
        JButton playButton = new JButton("Jouer");
        JButton quitButton = new JButton("Quitter");
        buttonPanel.add(levelList);
        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);
        buttonPanel.setOpaque(false);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Actions des boutons
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // récupérer le niveau choisi
                MF mf = new MF((String) levelList.getSelectedItem());
                if (mf.initialized) {
                    mf.setVisible(true);
                    setVisible(false);
                } else {
                    // afficher un message d'erreur
                    JOptionPane.showMessageDialog(null, "Vérifiez que le fichier est correctement écrit avant de relancer.", "Échec lors de la génération du niveau", JOptionPane.ERROR_MESSAGE);
                }
                
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
    }
}