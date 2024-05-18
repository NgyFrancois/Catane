import javax.swing.JFrame;
import java.util.Scanner;

public class Catane extends JFrame {
	public Plateau plateau;
	public Interface inter;
	public Terminale ter;
	public static Joueur[] joueur;
	public boolean gagne=false;
	public GameScreenV2 gameScreen;
	public Scanner sc;

	public Catane() {
		sc = new Scanner(System.in);
		plateau = new Plateau();
		this.gameScreen = new GameScreenV2(plateau);
		this.add(gameScreen);
	}

	public void jeuInit(){
		System.out.println("Jeu Interface Graphique('g') ou Terminale ('t') ?");
		String rep = sc.next();
		if(rep.equals("g")){
			sc.close();
			jeuInitG();
		}else if(rep.equals("t")){
			jeuInitT();
		}else{	
			System.out.println("Réponse incorrect ");
			jeuInit();
		}
	}

	public void jeuInitG(){
		sc.close();
		inter = new Interface();
	}

	public void jeuInitT(){
	 	ter = new Terminale();
	 	ter.JeuInit();
	}

	public void initWindow() {
		this.setSize(96*this.plateau.longueur, 96*this.plateau.largeur);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	public GameScreenV2 getScreen(){
		return gameScreen;
	}

	


	public static void main(String[] args) {
		Catane c = new Catane();
		c.plateau.initPlateau();
		//c.plateau.afficher();
		//c.initWindow();
		c.jeuInit();
		//Joueur j = new Joueur();
		//new Commerce(j);
	}
}

