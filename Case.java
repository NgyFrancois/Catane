import javax.swing.*;
import java.awt.*;

public class Case extends JButton{
	public Case[][] plateau;
	public 	String type;
	public 	String team;
	public 	Color carre;
	public 	int x;
	public 	int y;
	public 	int numero;
	public boolean hasRessource = false;

	public Case(String type,Case[][] plateau) {
		this.plateau = plateau;
		team="";
		numero = Plateau.getRandomInt(2, 12);
		this.type = type;
		// this.setText(type);
		// this.setBackground(carre);
		this.setVisible(true);

		this.addActionListener(e-> {
			//System.out.println("x"+x+",y"+y);
			croissement();
			intersection();
			upVille();
			genereRessource();
			//collecteTmp();
			collecte();
		});
	}

	public void croissement(){
		if(Interface.achatColonie>0){
			if(plateau[y][x].getType()=="Croissement"){
				if(team.equals("")){ // on verif qu'il y a pas déja qql
					this.setBackground(Interface.courant.couleur);
					this.type="Colonie";
					this.setIcon(TextureLoader.imageColonie);
					// this.setText("Colonie");
					Interface.achatColonie--;
					team=Interface.courant.nom;
					repaint();
				}
			}
		}
		// System.out.println(team);
	}

	public void intersection(){
		if(Interface.achatRoute>0){
			if(plateau[y][x].getType()=="Intersection"){
				if(verifRoute() && team.equals("")){ //on verifie qu'il peut construire une route et qu'il y en a pas déja une 
					this.setBackground(Interface.courant.couleur);
					this.type="Route";
					// this.setText("Route");
					if (this.y % 2 == 0) {
						this.setIcon(TextureLoader.imageRouteVerticale);
					} else {
						this.setIcon(TextureLoader.imageRouteHorizontale);
					}
					team=Interface.courant.nom;
					Interface.achatRoute--;
					repaint();
				}
			}
		}
	}

	public void upVille(){
		if(Interface.achatVille>0){
			if(type.equals("Colonie")&&team.equals(Interface.courant.nom)){
				Interface.courant.removeStructure("Colonie");
				Interface.courant.addStructure(new Ville(Interface.courant));
				Interface.achatVille--;
				this.type="Ville";
				// this.setText("Ville");
				this.setIcon(TextureLoader.imageVille);
				repaint();
			}
		}
	}

	public void genereRessource() {
		if (Interface.resDe != 0) {
			for (int i = 0; i<this.plateau.length; i+=2) {
				for (int j = 0; j<this.plateau[i].length; j+=2) {
					if (this.plateau[i][j].numero == Interface.resDe) {
						//Un Desert ne produit pas de ressource
						if (!this.plateau[i][j].type.equals("Desert")) {
							this.plateau[i][j].hasRessource = true;
						}
						switch (this.plateau[i][j].type) {
							case "Champ" :
								this.plateau[i][j].setIcon(TextureLoader.imageChampPlein);
								break;
							case "Colline":
								this.plateau[i][j].setIcon(TextureLoader.imageCollinePlein);
								break;
							case "Foret":
								this.plateau[i][j].setIcon(TextureLoader.imageForetPlein);
								break;
							case "Montagne":
								this.plateau[i][j].setIcon(TextureLoader.imageMontagnePlein);
								break;
							case "Pre":
								this.plateau[i][j].setIcon(TextureLoader.imagePrePlein);
								break;
						}
					}
				}
			}
			Interface.resDe = 0;
			repaint();
		}
	}

	public void test(){
		System.out.println(y+" "+x);
		System.out.println(plateau[y-1][x-1].x+" "+plateau[y-1][x-1].y);
		System.out.println(plateau[y+1][x-1].x+" "+plateau[y+1][x-1].y);
		System.out.println(plateau[y-1][x+1].x+" "+plateau[y-1][x+1].y);
		System.out.println(plateau[y+1][x+1].x+" "+plateau[y+1][x+1].y);
		System.out.println("++++++++++++++++++++++++");
	}


	public void collecte() {
		if (this.estTuile()) {
			if (this.hasRessource) {
				if (!this.plateau[limiteMoins(y)][limiteMoins(x)].team.equals("")) {
					addRessource(this.plateau[limiteMoins(y)][limiteMoins(x)].team,res(type));
					this.hasRessource=false;
					this.resetTexture();
					repaint();
					// System.out.println(this.plateau[limiteMoins(y)][limiteMoins(x)].team+"0");

				}if (!this.plateau[limitePlus(y)][limiteMoins(x)].team.equals("")) {
					addRessource(this.plateau[limitePlus(y)][limiteMoins(x)].team,res(type));
					this.hasRessource=false;
					this.resetTexture();
					repaint();
					// System.out.println(this.plateau[limitePlus(y)][limiteMoins(x)].team+"1");

				}if (!this.plateau[limiteMoins(y)][limitePlus(x)].team.equals("")) {
					addRessource(this.plateau[limiteMoins(y)][limitePlus(x)].team,res(type));
					this.hasRessource=false;
					this.resetTexture();
					repaint();
					// System.out.println(this.plateau[limiteMoins(y)][limitePlus(x)].team+"2");

				}if (!this.plateau[limitePlus(y)][limitePlus(x)].team.equals("")) {
					addRessource(this.plateau[limitePlus(y)][limitePlus(x)].team,res(type));
					this.hasRessource=false;
					this.resetTexture();
					repaint();
					// System.out.println(this.plateau[limitePlus(y)][limitePlus(x)].team+"3");
				}
			}
		}
	}

	
	public void collecteTmp() {
		if (this.estTuile()) {
			if (this.hasRessource) {
				this.resetTexture();
			}
		}
	}

	private void resetTexture() {
		// System.out.println("resetTexture :");
		// System.out.println(this.type);
		// System.out.println("plateau type :");
		// System.out.println(this.plateau[this.y][this.x].type);
		switch (this.type) {
			case "Champ" :
				this.setIcon(TextureLoader.imageChamp);
				break;
			case "Colline":
				this.setIcon(TextureLoader.imageColline);
				break;
			case "Desert":
				this.setIcon(TextureLoader.imageDesert);
				break;
			case "Foret":
				this.setIcon(TextureLoader.imageForet);
				break;
			case "Montagne":
				this.setIcon(TextureLoader.imageMontagne);
				break;
			case "Pre":
				this.setIcon(TextureLoader.imagePre);
				break;
			default	:
				this.setText("error");
				break;
		}
	}
	public void addRessource(String nom,String res){
		for(Joueur j:Catane.joueur){
			if(nom.equals(j.nom)){
				j.inventaire.addRessource(res);
			}
		}
	}

	//sert juste a donner le nom de la ressource correspondant au biome , comme on a pas eu le temps d'utiliser les Tuiles pour ca 
	public String res(String biome){
		if(biome.equals("Foret")) return "Bois";
		else if(biome.equals("Montagne")) return "Pierre";
		else if(biome.equals("Pre")) return "Laine";
		else if(biome.equals("Colline")) return "Argile";
		else if(biome.equals("Champ")) return "Ble";
		else return "";
	}

	public boolean estTuile() {
		return this.type.equals("Champ") || this.type.equals("Colline") ||
		this.type.equals("Desert") || this.type.equals("Foret") || this.type.equals("Montagne") || this.type.equals("Pre");
	}

	//Verifie si une route / colonie / ville se trouve a coter
	public boolean verifRoute(){
		if((plateau[limiteMoins(y)][x].team.equals(Interface.courant.nom) && !plateau[limiteMoins(y)][x].team.equals(""))
		|| (plateau[y][limiteMoins(x)].team.equals(Interface.courant.nom) && !plateau[y][limiteMoins(x)].team.equals(""))
		|| (plateau[y][limitePlus(x)].team.equals(Interface.courant.nom) && !plateau[y][limitePlus(x)].team.equals(""))
		|| (plateau[limitePlus(y)][x].team.equals(Interface.courant.nom) && !plateau[limitePlus(y)][x].team.equals(""))
		){
			return true;
		}else{
			return false;
		}
	}

	public boolean verifTeam(){
		if(this.plateau[limiteMoins(y)][limiteMoins(x)].team.equals(Interface.courant.nom) || this.plateau[limitePlus(y)][limiteMoins(x)].team.equals(Interface.courant.nom) ||
		this.plateau[limiteMoins(y)][limitePlus(x)].team.equals(Interface.courant.nom) || this.plateau[limitePlus(y)][limitePlus(x)].team.equals(Interface.courant.nom)){
			return true;
		}
		return false;
	}

	public int limiteMoins(int i){
		if(i==0){ 
			return i;
		}else{
			return i-1;
		}
	}

	public int limitePlus(int i){
		if(i==8){
			return i;
		}else{
			return i+1;
		}
	}

	public void setColor(Color c){
		this.carre = c;
		this.setBackground(c);
	}
	public String getType() {
		return type;
	}
	public String toString() {
		return type.substring(0, 1);
	}

}