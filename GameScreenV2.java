import java.awt.Color;
import java.awt.*;
import javax.swing.JPanel;

public class GameScreenV2 extends JPanel{
	public Plateau plateau;
    public Case[][] carre;

	public GameScreenV2(Plateau plateau) {
		this.plateau = plateau;
        carre = new Case[plateau.getTaille()][plateau.getTaille()];
        this.setLayout(new GridLayout(plateau.getTaille(),plateau.getTaille()));
        if (this.plateau != null) {
			for (int y = 0; y < this.plateau.getTaille(); y++) {
				for (int x = 0; x < this.plateau.getTaille(); x++) {
					switch (this.plateau.getCase(y,x).getType()) {
						case "Tuile" :
                            if(plateau.getCase(y,x)==null){
                                carre[y][x] = new Case("Tuile",carre);
                                carre[y][x].setColor(Color.BLUE);
                                this.add(carre[y][x]);
                                carre[y][x].y=y;
                                carre[y][x].x=x;
                          }else{
						    switch (((Tuile)this.plateau.getCase(y, x)).getBiome()) {
							case "Colline" :
                                carre[y][x] = new Case("Colline",carre);
                                carre[y][x].setIcon(TextureLoader.imageColline);
                                this.add(carre[y][x]);
                                carre[y][x].y=y;
                                carre[y][x].x=x;
							 	break;
							case "Desert" :
                                carre[y][x] = new Case("Desert",carre);
                                carre[y][x].setIcon(TextureLoader.imageDesert);
                                this.add(carre[y][x]);
                                carre[y][x].y=y;
                                carre[y][x].x=x;
								break;
							case "Foret" :
                                carre[y][x] = new Case("Foret",carre);
                                carre[y][x].setIcon(TextureLoader.imageForet);
                                this.add(carre[y][x]);
                                carre[y][x].y=y;
                                carre[y][x].x=x;
								break;
							case "Montagne" :
                                carre[y][x] = new Case("Montagne",carre);
                                carre[y][x].setIcon(TextureLoader.imageMontagne);
                                this.add(carre[y][x]);
                                carre[y][x].y=y;
                                carre[y][x].x=x;
								break;
							case "Pre" :
                                carre[y][x] = new Case("Pre",carre);
                                carre[y][x].setIcon(TextureLoader.imagePre);
                                this.add(carre[y][x]);
                                carre[y][x].y=y;
                                carre[y][x].x=x;
								break;
							case "Champ" :
                                carre[y][x] = new Case("Champ",carre);
                                carre[y][x].setIcon(TextureLoader.imageChamp);
                                this.add(carre[y][x]);
                                carre[y][x].y=y;
                                carre[y][x].x=x;
								break;
                            }
                        }
                        break;
						case "Croisement" :
                            carre[y][x] = new Case("Croissement",carre);
                            carre[y][x].setIcon(TextureLoader.imageCroisement);
                            this.add(carre[y][x]);
                            carre[y][x].y=y;
                            carre[y][x].x=x;
							break;
						case "Intersection" :
                            carre[y][x] = new Case("Intersection",carre);
                            if (y%2 == 0) {
                                carre[y][x].setIcon(TextureLoader.imagePathVertical);
                            } else {
                                carre[y][x].setIcon(TextureLoader.imagePathHorizontal);
                            }
                            this.add(carre[y][x]);
                            carre[y][x].y=y;
                            carre[y][x].x=x;
							break;
					}
				}
			}
            repaint();
		}	
	}

    public Case getCase(int y,int x){
        return carre[y][x];
    }




}