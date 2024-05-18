public class Croisement extends Case{
	Batiment batiment = null;

	public Croisement(){
		super("Croisement",null);
	}
	public void setBatiment(Joueur proprietaire, String type){
		switch(type){
			case "Colonie":
				this.batiment = new Colonie(proprietaire);
				break;
			case "Ville":
				this.batiment = new Ville(proprietaire);
				break;
			default:
				System.out.println("Unknow type");
				break;
		}
	}
}