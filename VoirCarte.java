import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;

public class VoirCarte extends JFrame{
JLabel[] titre;
Joueur courant;


public VoirCarte(Joueur j){
    courant = j;
    this.setSize(600,300);
    this.setVisible(true);
    this.setLayout(new GridLayout(2,5));

    //top (titre des ressources)
    titre = new JLabel[10];
    titre[0] = new JLabel("Argile", SwingConstants.CENTER);
    titre[0].setFont(new Font("Serif", Font.BOLD, 20));
    titre[0].setVisible(true);
    this.getContentPane().add(titre[0]);
    titre[1] = new JLabel("Ble", SwingConstants.CENTER);
    titre[1].setFont(new Font("Serif", Font.BOLD, 20));
    titre[1].setVisible(true);
    this.getContentPane().add(titre[1]);
    titre[2] = new JLabel("Bois", SwingConstants.CENTER);
    titre[2].setFont(new Font("Serif", Font.BOLD, 20));
    titre[2].setVisible(true);
    this.getContentPane().add(titre[2]);
    titre[3] = new JLabel("Laine", SwingConstants.CENTER);
    titre[3].setFont(new Font("Serif", Font.BOLD, 20));
    titre[3].setVisible(true);
    this.getContentPane().add(titre[3]);
    titre[4] = new JLabel("Pierre", SwingConstants.CENTER);
    titre[4].setFont(new Font("Serif", Font.BOLD, 20));
    titre[4].setVisible(true);
    this.getContentPane().add(titre[4]);

    //bottom nombre de ressource

    for(int i=5;i<10;i++){
        titre[i] = new JLabel("0", SwingConstants.CENTER);
        titre[i].setFont(new Font("Serif", Font.BOLD, 20));
        titre[i].setVisible(true);
        this.getContentPane().add(titre[i]);
    }

    titre[5].setText(courant.inventaire.nbRes("Argile"));
    titre[6].setText(courant.inventaire.nbRes("Ble"));
    titre[7].setText(courant.inventaire.nbRes("Bois"));
    titre[8].setText(courant.inventaire.nbRes("Laine"));
    titre[9].setText(courant.inventaire.nbRes("Pierre"));
    repaint();


}
}
