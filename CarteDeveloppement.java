public abstract class CarteDeveloppement{
private boolean utilisable;

public CarteDeveloppement(boolean utilisable){
	this.utilisable = utilisable;
}

public abstract void jouer();
}