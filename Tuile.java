public class Tuile extends Case{
	protected String biome;
	public CarteRessource ressource=null;//null if nothing is generated

	public Tuile(String biome) {
		super("Tuile",null);
		this.biome = biome;
	}

	public void addRessource(CarteRessource ressource){
		this.ressource=ressource;
	}
	public boolean hasRessource(){
		return this.ressource!=null;
	}
	public CarteRessource popRessource(){
		if(this.hasRessource()){
			CarteRessource tmp=this.ressource;
			this.ressource=null;
			return tmp;
		}
		return null;
	}

	public String getBiome() {
		return this.biome;
	}
	public String toString() {
		return this.biome.substring(0, 1);
	}
}
