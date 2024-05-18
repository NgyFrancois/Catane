import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class Interface extends Catane{
    private JFrame fenetre;
    private JPanel ecranHaut;
    private JPanel ecranBas;
    private JLabel question;
    private JButton[] button;
    private JSlider[] slider;
    private JTextField[] reponse;
    static Joueur courant;
    static int achatRoute=0;
    static int achatVille=0;
    static int achatColonie=0;
    static int resDe = 0;
    static boolean lancer = false;

    public Interface(){
        //Partie fenetre
        fenetre = new JFrame("CATANE");
        fenetre.setSize(800,800);
        fenetre.setVisible(true);
        fenetre.setLayout(new GridLayout(2,1));
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        //Partie dans la fenetre ( ecran )
        ecranHaut = new JPanel();
        ecranHaut.setLayout(new BorderLayout());
        ecranHaut.setBackground(new Color(190,222,200));
        fenetre.add(ecranHaut); 

        ecranBas = new JPanel();
        ecranBas.setLayout(new GridLayout(1,2));
        ecranBas.setBackground(new Color(20,50,60));
        fenetre.add(ecranBas);  

        //Partie Titre / Question
        question = new JLabel();
        question = new JLabel("Voulez-vous jouer ?", SwingConstants.CENTER); // pour ajuster au centre ( horizontalement )
        question.setFont(new Font("Serif", Font.BOLD, 30));
        ecranHaut.add(question,BorderLayout.CENTER);    

        //Partie Reponse
        button = new JButton[2];
        button[0] = new JButton("OUI");
        button[1] = new JButton("NON");
        ecranBas.add(button[0]);
        ecranBas.add(button[1]);    

        //Fonction Button
        button[1].addActionListener(e-> {
            fenetre.dispose();
        }); 

        button[0].addActionListener(e-> {
            demanderJoueur();
        }); 

    }   

    /*          Methode supprimer car on a pas eu le temps de l'implementer
    public void demanderPlateauG(){
        //on supprime les boutons qu'on avait / maj de la question ( J'ai recree le button oui car je sais pas encore comment actuliser les ActionListener)
        ecranBas.remove(button[0]);
        button[0]=new JButton();
        ecranBas.remove(button[1]);
        question.setText("Quelle taille de plateau voulez-vous ?"); 

        ecranBas.setLayout(new GridLayout(2,1));
        slider = new JSlider[1];
        slider[0] = new JSlider(5,20,8);
        slider[0].setMajorTickSpacing(1);
        slider[0].setPaintTrack(true);
        slider[0].setPaintTicks(true);
        slider[0].setPaintLabels(true);
        ecranBas.add(slider[0]);
        ecranBas.add(button[0]);
        button[0].setText("Valider");
        fenetre.repaint();  

        button[0].addActionListener(e-> {
            plateau = new Plateau();
            plateau.initPlateau(slider[0].getValue());
           demanderJoueur();
        });
    }
    */  
    

    public void demanderJoueur(){
           //on supprime les boutons qu'on avait / maj de la question
           ecranBas.remove(button[1]);
           ecranBas.remove(button[0]);
           button[0]=new JButton("Valider");
           question.setText("Combien de Joueur ?");
           ecranBas.setLayout(new GridLayout(2,1)); 

           slider = new JSlider[1];
           slider[0] = new JSlider(2,4,2);
           slider[0].setMajorTickSpacing(1);
           slider[0].setPaintTrack(true);
           slider[0].setPaintTicks(true);
           slider[0].setPaintLabels(true);
           ecranBas.add(slider[0]);
           ecranBas.add(button[0]);
           fenetre.repaint();  
           fenetre.pack(); 

           button[0].addActionListener(e-> {
               InitJoueur(slider[0].getValue());
           });
    }   

    public void InitJoueur(int nbre){
        ecranHaut.remove(question);
        ecranBas.remove(button[0]);
        ecranBas.remove(slider[0]);
        button[0] = new JButton("Commencer");
        ecranBas.add(button[0]);
        ecranHaut.setLayout(new GridLayout(2,nbre)); //nbre de joueur et 2 pour les champs de chaque joueur
        joueur = new Joueur[nbre];  

        reponse = new JTextField[nbre];
        for(int i=0;i<nbre;i++){
            reponse[i] = new JTextField("Joueur"+(i+1));
            reponse[i].setFont(new Font("Serif",Font.BOLD,27));
            ecranHaut.add(reponse[i]);
        }   

        slider = new JSlider[nbre];
        for(int i=0;i<nbre;i++){
            slider[i] = new JSlider(10,70,18);
            slider[i].setMajorTickSpacing(10);
            slider[i].setMinorTickSpacing(1);
            slider[i].setPaintTrack(true);
            slider[i].setPaintTicks(true);
            slider[i].setPaintLabels(true);
            ecranHaut.add(slider[i]);
        }   

        fenetre.repaint(); 
        fenetre.pack(); 

        button[0].addActionListener(e->{
            //initialise chaque joueur
            
            for(int i=0;i<nbre;i++){
                joueur[i] = new Joueur(false);
                joueur[i].setNom(reponse[i].getText());
                joueur[i].setAge(slider[i].getValue());
                //apres je supprime mes slider/reponses car j'en aurais plus besoin
                ecranHaut.remove(slider[i]);
                ecranHaut.remove(reponse[i]);
            }
            slider = new JSlider[0];
            reponse = new JTextField[0];
            triAge();
            initWindow();
            debut(0,2,2); // le joueur 0 commence
        }); 

    }   

    public void debut(int j,int colo,int route){
        //on suppr les truc slider/texte qu'on avait
        ecranBas.remove(button[0]);
        ecranHaut.remove(question);
        
        question = new JLabel(joueur[j].nom+" Placer vos 2 colonies/routes", SwingConstants.CENTER);
        question.setFont(new Font("Serif", Font.BOLD, 30));
        ecranHaut.add(question,BorderLayout.CENTER);
        button[0] = new JButton("Valider");
        button[0].setVisible(true);
        ecranBas.add(button[0]);    

            courant = joueur[j];
            joueur[j].addStructure(new Route(joueur[j]));
            joueur[j].addStructure(new Route(joueur[j]));
            joueur[j].addStructure(new Colonie(joueur[j]));
            joueur[j].addStructure(new Colonie(joueur[j]));
            achatColonie=colo;
            achatRoute=route;   

            fenetre.repaint();
            fenetre.pack();  

            

                if(j<joueur.length-1){
                    button[0].addActionListener(e->{
                        if(achatColonie==0 && achatRoute==0){
                         debut(j+1,2,2);
                        }
                    });
                }else{
                    button[0].addActionListener(e->{
                        if(achatColonie==0 && achatRoute==0){
                             action(0);
                         }
                    });
                }
        
    }   

    public void action(int j){
        //on suppr les truc slider/texte qu'on avait
        resDe = 0;
        button[1].setEnabled(true);

        fenetre.remove(ecranHaut);  

        ecranBas.remove(button[0]); 

        courant = joueur[j];
        //Parti haut
        ecranHaut = new JPanel(new GridLayout(2,2));
        fenetre.add(ecranHaut);
        button = new JButton[7];
        button[1] = new JButton("Lancer Des");
        button[2] = new JButton("Construire");
        button[3] = new JButton("Commerce");
        button[4] = new JButton("Voir carte");  

        ecranHaut.add(button[1]);
        ecranHaut.add(button[2]);
        ecranHaut.add(button[3]);
        ecranHaut.add(button[4]);   
    

        //Parti bas
        fenetre.remove(ecranBas);
        ecranBas.setLayout(new BorderLayout()); 
        fenetre.add(ecranBas);
        button[0] = new JButton(joueur[j].getNom()+"  Fin du tour");
        ecranBas.add(button[0]);    

        this.setButtonColor(j);

        fenetre.repaint();
        fenetre.pack(); 

        //action des button dessus
        //button fin de tour
        if(achatColonie==0&&achatRoute==0&&achatVille==0){ 
            if(j==joueur.length-1){
                button[0].addActionListener(e->{ 
                action(0);
                });
                }else{
                    button[0].addActionListener(e->{ 
                        action(j+1);
                    });
                }
        }   
        button[1].addActionListener(e->{
            button[1].setEnabled(false);
            resDe = Plateau.getRandomInt(2, 12);
            // System.out.println(resDe);

        });
        button[3].addActionListener(e->{
            new Commerce(courant); //ouvre une page commerce
        }); 

        button[2].addActionListener(e->{
            new Construire(courant);
        }); 

        button[4].addActionListener(e->{
            new VoirCarte(courant);
            System.out.println(courant.inventaire.ressource.size());
        }); 


        if(courant.getPoint()>=10){
            JFrame win = new JFrame();
            win.setSize(800,800);
            win.setLayout(new BorderLayout());
            JLabel fin =  new JLabel(courant+" a gagné !", SwingConstants.CENTER);
            fin.setFont(new Font("Serif", Font.BOLD, 30));
            fin.setVisible(true);
            win.add(fin,BorderLayout.CENTER);
        }
    

    }
    private void setButtonColor(int j) {
        for (int i = 0; i < 5; i++) {
            button[i].setBackground(this.joueur[j].couleur);
        } 
    }

    //Pour faire jouer les joueur du plus petit au plus grand apres
    public void triAge(){
        for(int i=0;i<joueur.length;i++){
            for(int j=0;j<joueur.length;j++){
                if(joueur[i].getAge() < joueur[j].getAge()){
                    Joueur tmp = joueur[i];
                    joueur[i] = joueur[j];
                    joueur[j] = tmp;
                }
            }
        }
    }
}