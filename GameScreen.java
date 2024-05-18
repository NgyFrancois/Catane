import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameScreen extends JPanel{
	public Plateau plateau;

	public GameScreen(Plateau plateau) {
		this.plateau = plateau;
	}
	public void paintComponent(Graphics graphique) {
		super.paintComponent(graphique);
		this.paintPlateau(graphique);
	}
	private void paintPlateau(Graphics graphique) {
		if (this.plateau != null) {
			for (int y = 0; y < this.plateau.largeur; y++) {
				for (int x = 0; x < this.plateau.longueur; x++) {
					switch (this.plateau.getCase(x, y).getType()) {
						case "Tuile" :
							// switch (this.plateau.getCase(x, y).getBiome()) {
							// 	case "Colline" :
							// 		graphique.setColor(Color.CYAN);
							// 		break;
							// 	case "Desert" :
							// 		graphique.setColor(Color.YELLOW);
							// 		break;
							// 	case "Foret" :
							// 		graphique.setColor(Color.GREEN);
							// 		break;
							// 	case "Montagne" :
							// 		graphique.setColor(Color.GRAY);
							// 		break;
							// 	case "Pre" :
							// 		graphique.setColor(Color.MAGENTA);
							// 		break;
							// 	case "Champ" :
							// 		graphique.setColor(Color.PINK);
							// 		break;
							// 	default :
							// 		graphique.setColor(Color.BLUE);
							// 		break;
							// }
							graphique.setColor(Color.BLUE);
							break;
						case "Croisement" :
							graphique.setColor(Color.RED);
							break;
						case "Intersection" :
							graphique.setColor(Color.ORANGE);
							break;
						default :
							graphique.setColor(Color.BLACK);
					}
					graphique.fillRect(x * 96, y * 96, 96, 96);
				}
			}
		}	
	}
}