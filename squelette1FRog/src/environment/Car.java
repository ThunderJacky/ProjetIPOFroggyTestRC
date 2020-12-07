package environment;

import java.awt.Color;
import util.Case;
import gameCommons.Game;
import graphicalElements.Element;


public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	public Car(Game game, Case Position, boolean LtR) {
		this.game = game;
		this.length = game.randomGen.nextInt(3)+1;
		this.leftToRight = LtR;
		this.leftPosition = new Case( LtR ? Position.absc - this.length : Position.absc, Position.ord);
	}
	
	public void move(boolean b) {
		if (b) {
			this.leftPosition = new Case(this.leftPosition.absc +(this.leftToRight ? 1 : -1), this.leftPosition.ord);
		}
		this.addToGraphics();
	}

	public boolean surCase(Case c) {
		if (c.ord != this.leftPosition.ord) {
			return false;
		}else{
			return c.absc >= this.leftPosition.absc && c.absc < this.leftPosition.absc + this.length;
		}
	}


	public boolean pop() {
		return this.leftPosition.absc + this.length > 0 || this.leftPosition.absc < this.game.width;
	}
	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
