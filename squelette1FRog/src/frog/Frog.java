package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

import static util.Direction.*;

public class Frog implements IFrog {
	
	private Game game;
	private Case myPosition;
	private Direction dir;

	public Frog(Game game) {
		this.game = game;
		this.myPosition = new Case(0,0);
		this.dir = up;
	}


	@Override
	public Case getPosition() {
		return this.myPosition;
	}

	public Direction getDirection() {
		return this.dir;
	}

	@Override
	public void move (Direction key) {
		if (key.equals(Direction.down)) {
			if(this.myPosition.ord > 0) {
				this.myPosition.ord -= 1;
				this.dir = down;
			}
		} else if (key.equals(up)) {
			this.myPosition.ord += 1;
			this.dir = up;
		} else if (key.equals(Direction.left)) {
			if(this.myPosition.absc > 0) {
				this.myPosition.absc -= 1;
				this.dir = left;
			}
		} else if (key.equals(Direction.right)) {
			if(this.myPosition.absc < game.width-1) {
				this.myPosition.absc += 1;
				this.dir = right;
			}
		}
	}

}
