public class Terminale extends Catane{

public Terminale(){
    super();
}

public void JeuInit(){
	System.out.println("Voulez vous jouer ? (oui/non)");
	String s = sc.nextLine();
	if(s.equals("non")){
		sc.close();
		return;
	}else if(s.equals("oui")){
		demanderPlateau();
		demanderJoueur();
		action();
	}else{
		System.out.println("Réponse Incorrect");
			JeuInit();
	}
}

public void demanderPlateau(){
	System.out.println("Voulez vous personalisé la taille de votre tableau ? (oui/non)");
	String s = sc.nextLine();
		if(s.equals("non")){
			plateau.initPlateau();
		}else if(s.equals("oui")){
			System.out.println("Quelle taille voulez-vous ?");
			plateau.initPlateau(/*sc.nextInt()*/);
		}else{
			System.out.println("Réponse incorrect");
			demanderPlateau();
		}
}

public void demanderJoueur(){
	System.out.println("Combien de joueur ya t-il ?");
	int nbJoueur = sc.nextInt();
	joueur = new Joueur[nbJoueur];
	if(nbJoueur <2 || nbJoueur >4){
		System.out.println("nombre de joueur invalide,veuillez rentrer un nombre entre 2 et 4");
		demanderJoueur();
	}else{
		for(int i=0;i<nbJoueur;i++){
            joueur[i] = new Joueur();
             joueur[i].initJoueur(i);
          }
     }
}

// pour l'instant meme si le joueur a gagne tant que les tour de tout le monde ne sont pas passé ca continue 
public void action(){
	while(!gagne){
        for(int i=0;i<joueur.length;i++){
            gagne(joueur[i]);
            plateau.afficher();
            joueur[i].action(false);
        }
	}
	sc.close();
	System.out.println("Fin de la partie !");
}

public void gagne(Joueur j){
	if(j.getPoint() > 20)
		gagne = true;
}

    
}
