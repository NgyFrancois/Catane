
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;

public class Commerce extends JFrame{
boolean port=false;
boolean echange=false;
JPanel top;
JLabel titre;
JPanel ressource;
JButton[] button;
Joueur courant;
String materiaux;


public Commerce(Joueur j){
    courant = j;
    this.setSize(600,300);
    this.setVisible(true);
    this.setLayout(new GridLayout(2,1));
    //top
    top = new JPanel();
    top.setBackground(Color.ORANGE);
    top.setLayout(new BorderLayout());
    top.setVisible(true);
    titre = new JLabel("Quelle matériaux voulez-vous ?", SwingConstants.CENTER);
    titre.setFont(new Font("Serif", Font.BOLD, 20));
    titre.setVisible(true);

    //bot
    ressource = new JPanel();
    ressource.setLayout(new GridLayout(1,5));
    ressource.setVisible(true);
    button = new JButton[6];
    button[0] = new JButton("Argile");
    button[1] = new JButton("Blé");
    button[2] = new JButton("Bois");
    button[3] = new JButton("Laine");
    button[4] = new JButton("Pierre");

    ressource.add(button[0]);
    ressource.add(button[1]);
    ressource.add(button[2]);
    ressource.add(button[3]);
    ressource.add(button[4]);
    top.add(titre);
    System.out.println("test");
    this.getContentPane().add(top);
    this.getContentPane().add(ressource);

    button[0].addActionListener(e->{
        CommerceArgile();
    });
    button[1].addActionListener(e->{
        CommerceBle();
    });
    button[2].addActionListener(e->{
        CommerceBois();
    });
    button[3].addActionListener(e->{
        CommerceLaine();
    });
    button[4].addActionListener(e->{
        CommercePierre();
    });

    
}

public void CommerceArgile(){
    if(!echange){
        materiaux = "Argile";
        titre.setText("Contre quelle matériaux ?");
        repaint();
        echange=true;
    }else{
        int montant = 4;
        if(port) montant--; //si il y a un port les "frais" de commerce sont moins chers
        if(courant.inventaire.hasRessource(materiaux,montant)){
            courant.inventaire.popRessource(materiaux,montant);
            courant.inventaire.addRessource("Argile");
            titre.setText("Achat validé,Quelle matériaux voulez-vous ?");
            repaint();
            echange=false;
        }else{
            echange=false;
            titre.setText("Achat echoué,Quelle matériaux voulez-vous ?");
            repaint();
        }
    }
}

public void CommerceBle(){
    if(!echange){
        materiaux = "Ble";
        titre.setText("Contre quelle matériaux ?");
        repaint();
        echange=true;
    }else{
        int montant = 4;
        if(port) montant--; //si il y a un port les "frais" de commerce sont moins chers
        if(courant.inventaire.hasRessource(materiaux,montant)){
            courant.inventaire.popRessource(materiaux,montant);
            courant.inventaire.addRessource("Ble");
            titre.setText("Achat validé,Quelle matériaux voulez-vous ?");
            repaint();
            echange=false;
        }else{
            echange=false;
            titre.setText("Achat echoué,Quelle matériaux voulez-vous ?");
            repaint();
        }
    }
}

public void CommerceBois(){
    if(!echange){
        materiaux = "Bois";
        titre.setText("Contre quelle matériaux ?");
        repaint();
        echange=true;
    }else{
        int montant = 4;
        if(port) montant--; //si il y a un port les "frais" de commerce sont moins chers
        if(courant.inventaire.hasRessource(materiaux,montant)){
            courant.inventaire.popRessource(materiaux,montant);
            courant.inventaire.addRessource("Bois");
            titre.setText("Achat validé,Quelle matériaux voulez-vous ?");
            repaint();
            echange=false;
        }else{
            echange=false;
            titre.setText("Achat echoué,Quelle matériaux voulez-vous ?");
            repaint();
        }
    }
}

public void CommerceLaine(){
    if(!echange){
        materiaux = "Laine";
        titre.setText("Contre quelle matériaux ?");
        repaint();
        echange=true;
    }else{
        int montant = 4;
        if(port) montant--; //si il y a un port les "frais" de commerce sont moins chers
        if(courant.inventaire.hasRessource(materiaux,montant)){
            courant.inventaire.popRessource(materiaux,montant);
            courant.inventaire.addRessource("Laine");
            titre.setText("Achat échoué,Quelle matériaux voulez-vous ?");
            repaint();
            echange=false;
        }else{
            echange=false;
            titre.setText("Achat echoué,Quelle matériaux voulez-vous ?");
            repaint();
        }
    }
}

public void CommercePierre(){
    if(!echange){
        materiaux = "Pierre";
        titre.setText("Contre quelle matériaux ?");
        repaint();
        echange=true;
    }else{
        int montant = 4;
        if(port) montant--; //si il y a un port les "frais" de commerce sont moins chers
        if(courant.inventaire.hasRessource(materiaux,montant)){
            courant.inventaire.popRessource(materiaux,montant);
            courant.inventaire.addRessource("Pierre");
            titre.setText("Achat échoué,Quelle matériaux voulez-vous ?");
            repaint();
            echange=false;
        }else{
            echange=false;
            titre.setText("Achat echoué,Quelle matériaux voulez-vous ?");
            repaint();
        }
    }
}
    
}
