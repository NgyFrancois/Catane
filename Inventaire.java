import java.util.LinkedList;

public class Inventaire{
	LinkedList<CarteRessource> ressource;
	LinkedList<CarteDeveloppement> developpement;

	public Inventaire(){
		this.ressource=new LinkedList<CarteRessource>();
		this.developpement=new LinkedList<CarteDeveloppement>();
	}

	public String nbRes(String s){
		int i=0;
		for(CarteRessource r:ressource){
			if(r.type.equals(s)){
				i++;
			}
		}
		return String.valueOf(i);
	}

	public boolean hasRessource(String type){
		// TEST :return this.ressource.contains(type)
		for(CarteRessource c:this.ressource){
			if(c.getType().equals(type)){
				return true;
			}
		}
		return false;
	}
	//Ce sera utile plus tard pour la creation de structures
	public boolean hasRessource(String type, int n){
		for(CarteRessource c:this.ressource){
			if(c.getType().equals(type)){
				n--;
				if(n<=0){
					return true;
				}
			}
		}
		return false;
	}
	//return CarteRessource au lieu de void, ce sera utile pour implementer les echanges entre deux joueurs
	public CarteRessource popRessource(String type){//return null si il n'y a rien a pop
		if(hasRessource(type)){
			for(CarteRessource c:this.ressource){
				if(c.getType().equals(type)){
					this.ressource.remove(c);
					return c;
				}
			}
		}
		return null;
	}
	public CarteRessource[] popRessource(String type, int n){//return un tableau vide si il n'y a pas aasez ne ressource
		CarteRessource[] tmp=new CarteRessource[n];
		if(hasRessource(type, n)){
			for(int i=0; i<this.ressource.size(); i++){
				if(this.ressource.get(i).getType().equals(type)){
					if(n > 0){
						tmp[n-1] = this.ressource.get(i);
						this.ressource.remove(i);
						i--;
						n--;
					}
				}
			}
		}
		return tmp;
	}

	public void addRessource(String type){
		ressource.add(new CarteRessource(type));
	}

	public void AfficherRessource(){
		for(int i=0;i<ressource.size();i++){
			System.out.print(ressource.get(i)+" ");
		}
	}

	public void AfficherDeveloppement(){
		for(int i=0;i<developpement.size();i++){
			System.out.print(developpement.get(i)+" ");
		}
	}
}