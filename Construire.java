import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;

public class Construire extends JFrame{
JPanel top;
JPanel bottom;
JLabel titre;
JButton[] button;
Joueur courant;


public Construire(Joueur j){
    courant = j;
    this.setSize(600,300);
    this.setVisible(true);
    this.setLayout(new GridLayout(2,1));
    //top
    top = new JPanel();
    top.setBackground(Color.ORANGE);
    top.setLayout(new BorderLayout());
    top.setVisible(true);
    titre = new JLabel("Que voulez vous construire ?", SwingConstants.CENTER);
    titre.setFont(new Font("Serif", Font.BOLD, 20));
    titre.setVisible(true);

    //bot
    bottom = new JPanel();
    bottom.setLayout(new GridLayout(1,3));
    bottom.setVisible(true);
    button = new JButton[6];
    button[0] = new JButton("Route");
    button[1] = new JButton("Colonie");
    button[2] = new JButton("Ville");

    bottom.add(button[0]);
    bottom.add(button[1]);
    bottom.add(button[2]);
    top.add(titre);
    this.getContentPane().add(top);
    this.getContentPane().add(bottom);
    repaint();

    button[0].addActionListener(e->{
        if(verifRoute()){
            courant.addStructure(new Route(courant));
            courant.inventaire.popRessource("Argile", 1);
            courant.inventaire.popRessource("Bois", 1);
            Interface.achatRoute++;
            titre.setText("Achat Confirmé");
        }else{
            titre.setText("Achat refusé");
        }
    });    

    button[1].addActionListener(e->{
        if(verifColonie()){
            courant.addStructure(new Colonie(courant));
            courant.inventaire.popRessource("Argile", 1);
            courant.inventaire.popRessource("Bois", 1);
            courant.inventaire.popRessource("Ble", 1);
            courant.inventaire.popRessource("Laine", 1);
            Interface.achatColonie++;
            titre.setText("Achat Confirmé");
        }else{
            titre.setText("Achat refusé");
        }
    });   

    button[2].addActionListener(e->{
        if(verifVille()){
            courant.addStructure(new Ville(courant));
            courant.inventaire.popRessource("Ble", 2);
            courant.inventaire.popRessource("Pierre", 3);
            Interface.achatVille++;
            titre.setText("Achat Confirmé");
        }else{
            titre.setText("Achat refusé");
        }
    });   
}


public boolean verifRoute(){
    return courant.PayeStructure("route");
}

public boolean verifColonie(){
    return courant.PayeStructure("colonie");
}

public boolean verifVille(){
    return courant.PayeStructure("ville");
}
}
