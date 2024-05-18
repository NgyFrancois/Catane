import java.util.Scanner;
import java.awt.Color;
public class Joueur{
	int age;
	String nom;
	Inventaire inventaire;
	Scanner sc;
	Structure [] structure;
	static int id = 0;
	Color couleur;

//version sans interface graphique

public Joueur(){
	inventaire = new Inventaire();
	structure = new Structure[10]; //10 car a partir de 10 structure on peut gagner 
	sc = new Scanner(System.in);
	id++;
}

//version avec interface graphique / J'ai mis un boolean qui fait rien mais c'est juste pour differencier nos 2 constructeur
public Joueur(boolean useless){
	inventaire = new Inventaire();
	structure = new Structure[50];
	Color[] tab= {Color.BLUE,Color.RED,Color.GREEN,Color.BLACK}; // pour les couleur des team
	couleur = tab[id];
	id++;
}

public void setNom(String nom){
	this.nom=nom;
}

public void setAge(int age){
	this.age=age;
}

public String getNom(){
	return nom;
}

//Les fonction en bas sont pour le jeu version scanner / Terminal
public void initJoueur(int numero){
	this.id = numero;
	System.out.println("Nom du joueur"+this.id);
	String nom = sc.nextLine();
	this.nom = nom;
	System.out.println("Age du joueur"+this.id);
	int age = sc.nextInt();
	this.age = age;
}

public int getPoint(){
	int point=0;
	//calcul point structure
	for(int i=0;i<structure.length;i++){
		if(structure[i]!=null){
			if(structure[i] instanceof Ville) point +=2;
			if(structure[i] instanceof Colonie) point++;
		}
	}
	//calcul point carte 
	//a faire
	return point;
}

public void action(boolean des){
	System.out.println("Au tour de "+nom);
	System.out.println("1(Lancer des), 2(Commerce), 3(Construire), 4(Voir carte ressource), 5(Voir carte dev)");
	int i = sc.nextInt();
	if(i<0 || i>5){
		System.out.println("action incorrect veuillez réessayer");
		action(des);
	}
	switch(i){
		case 1: 
			// a faire fonction lancer des random etc
		case 2:	
			// a faire fonction commerce
		case 3:
			// a faire fonction construire
		case 4:
			voirCarteR();
		case 5:
			voirCarteD();
	}
	if(des){
		System.out.println("Voulez-vous finir votre tour ?");
		String rep = sc.nextLine();
		if(rep.equals("oui")){
			return;
		}else if(rep.equals("non")){
			action(true);
		}
	}
}

public void voirCarteR(){
	System.out.println("Carte ressource de "+nom+" :");
	inventaire.AfficherRessource();
}

public void voirCarteD(){
	System.out.println("Carte Developpement de "+nom+" :");
	inventaire.AfficherDeveloppement();
}

public int getAge(){
	return age;
}

public boolean PayeStructure(String structure){
	if(structure.equals("ville")){
		return (inventaire.hasRessource("Pierre", 3)&&inventaire.hasRessource("Ble", 2));
	}
	if(structure.equals("route")){
		return (inventaire.hasRessource("Argile", 1)&&inventaire.hasRessource("Bois", 1));
	}
	if(structure.equals("colonie")){
		return (inventaire.hasRessource("Argile", 1)&&inventaire.hasRessource("Ble", 1)&&inventaire.hasRessource("Bois", 1)&&inventaire.hasRessource("Laine", 1));
	}
	return false;
}

public void addStructure(Structure s){
		int i=0;
		//on cherche une place dans le tableau pour rajouter une stucture
		while(i<50){
			if(structure[i]==null){
				structure[i]=s;
				return;
			}else{
				i++;
			}
		}
	}

	public void removeStructure(String s){
		int i=0;
		while(i<50){
			if(structure[i].getType().equals(s)){
				structure[i]=null;
				return;
			}
			i++;
		}
	}
}
