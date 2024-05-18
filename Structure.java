public class Structure{
	Joueur proprietaire;
	String type;
	char terminalSymbol;

	protected Structure(Joueur proprietaire, String type, char terminalSymbol){
		this.type=type;
		this.terminalSymbol=terminalSymbol;
	}
	public String getType(){
		return type;
	}
	public String toString(){
		return type+" "+ terminalSymbol;
	}
	public Joueur getProprietaire(){
		return this.proprietaire;
	}
}