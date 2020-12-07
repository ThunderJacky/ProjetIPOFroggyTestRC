package environment;

import java.util.ArrayList;
import util.Case;
import gameCommons.Game;
import java.util.Iterator;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int temps;

	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = density;
		for(int i = 0; i < 4 * game.width; i++) {
			this.move(true);
			this.mayAddCar();
		}
	}

	public Lane(Game game, int ord) {
		this(game, ord, game.defaultDensity);
	}

	public void update() {
		this.temps++;
		if(this.temps != this.speed) {// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
			this.move(false);  // d'horloge" �gal � leur vitesse
		} else {
			this.move(true);
			this.mayAddCar();  // A chaque tic d'horloge, une voiture peut �tre ajout�e
			this.temps = 0;
		}
		// Notez que cette m�thode est appel�e � chaque tic d'horloge
		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

	}

	public boolean isSafe(Case c) {
		Iterator i = this.cars.iterator();
		while(i.hasNext()) {
			Car car = (Car)i.next();
			if(car.surCase(c)) {
				return false;
			}
		}
		return true;
	}


	private void move(boolean b) {
		Iterator i = this.cars.iterator();
		while(i.hasNext()) {
			Car car = (Car)i.next();
			car.move(b);
		}
		this.enlever();
	}

	private void enlever() {
		ArrayList<Car> retirer = new ArrayList();
		Iterator i = this.cars.iterator();
		Car car;
		while(i.hasNext()) {
			car = (Car)i.next();
			if (!car.pop()) {
				retirer.add(car);
			}
		}
		i = retirer.iterator();
		while(i.hasNext()) {
			car = (Car)i.next();
			this.cars.remove(car);
		}
	}

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
