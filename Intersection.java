public class Intersection extends Case{
	Route route = null;

	public Intersection(){
		super("Intersection",null);
	}

	public void setRoute(Joueur proprietaire){
		this.route = new Route(proprietaire);
	}
	public boolean hasRoute(){
		return route != null;
	}
	// @Override
	// public String toString(){
	// 	if(this.route != null){
	// 		return this.route.toString();
	// 	}
	// 	return "!";
	// }
}