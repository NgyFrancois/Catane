import java.util.Stack;
import java.util.Random;
import javax.swing.JFrame;

public class Plateau extends JFrame{
	public final int longueur;
	public final int largeur;
	private Case[][] plateau;

	public Plateau(int cote) {
		if (cote < 5)	cote = 5;
		this.longueur = cote*2-1;
		this.largeur = cote*2-1;
		this.plateau = new Case[longueur][largeur];
		this.initPlateau();
	}
	public Plateau() {
		this(5);
	}
	//De base un plateau va etre de 5x5 tuile
	public void initPlateau() {
		this.initTuile();
		this.initStructure();
	}
	private void initStructure() {
		//On set up les Croisements
		for(int i=1; i<this.plateau.length; i+=2) {
			for(int j=1; j<this.plateau[i].length; j+=2) {
				this.plateau[i][j] = new Croisement();
			}
		}
		//On set up les intersections
		for(int i=0; i<this.plateau.length; i+=2) {
			for(int j=1; j<plateau[i].length; j+=2) {
				this.plateau[i][j] = new Intersection();
			}
		}
		for(int i=1; i<this.plateau.length; i+=2) {
			for(int j=0; j<this.plateau[i].length; j+=2) {
				this.plateau[i][j] = new Intersection();
			}
		}
	}
	private void initTuile() {
		//On set up les Tuiles
		Stack<Tuile> tuiles = this.getStackTuile();
		for(int i=0; i<this.plateau.length; i+=2) {
			for(int j=0; j<this.plateau[i].length; j+=2) {
				this.plateau[i][j] = tuiles.pop();
			}
		}
	}
	private static Stack<Tuile> getStackTuile() {
		Stack<Tuile> stack = new Stack<Tuile>();
		//Il faut au minimum :
		//	-5 Fôrets
		//	-4 Montagnes
		//	-5 paturages(Pre)
		//	-4 collines
		//	-5 champ
		//	-2 deserts
		//Le reste du tableau pourras donc être rempli au hazard
		int foret = 5;
		int montagne = 4;
		int paturage = 5;
		int colline = 5;
		int champ = 5;
		int desert = 1;

		while (foret != 0 || montagne != 0 || paturage != 0 || colline != 0 || champ != 0 || desert != 0) {
			if (foret != 0 && !skipType(getRandomInt(0, 10))) {
				stack.push(new TuileForet());
				foret--;
			} else if (montagne != 0 && !skipType(getRandomInt(0, 10))) {
				stack.push(new TuileMontagne());
				montagne--;
			} else if (paturage != 0 && !skipType(getRandomInt(0, 10))) {
				stack.push(new TuilePre());
				paturage--;
			} else if (colline != 0 && !skipType(getRandomInt(0, 10))) {
				stack.push(new TuileColline());
				colline--;
			} else if (champ != 0 && !skipType(getRandomInt(0, 10))) {
				stack.push(new TuileChamp());
				champ--;
			} else if (desert != 0 && !skipType(getRandomInt(0, 10))) {
				stack.push(new TuileDesert());
				desert--;
			}
		}
		return stack;
	}
	//Max et Min sont inclus dans les valeures de retour possibles
	public static int getRandomInt(int min,int max) {
		Random rand= new Random();
		return rand.nextInt(max - min + 1) + min;
	}
	private static boolean skipType(int x) {
		return x < 10;
	}

	public int getTaille() {
		return plateau.length;
	}

	public Case getCase(int x,int y) {
		return this.plateau[x][y];
	}

	public void afficher(){
		for(int i=0;i<this.plateau.length;i++) {
			for(int j=0;j<this.plateau[i].length;j++) {
				System.out.print(this.getCase(i, j) + " ");
			}
			System.out.println("");
		}
	}
}
