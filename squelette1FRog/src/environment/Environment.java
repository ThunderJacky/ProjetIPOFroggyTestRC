package environment;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import java.util.Iterator;

public class Environment implements IEnvironment {
    private Game game;
    private ArrayList<Lane> Lines;

    public Environment(Game game) {
        this.game = game;
        this.Lines = new ArrayList();
        this.Lines.add(new Lane(game,0,0.0D));

        for(int i = 1; i < game.height-1; i++) {
            this.Lines.add(new Lane(game, i));
        }

        this.Lines.add(new Lane(game, game.height-1, 0.0D ));
    }

 /*   public boolean isWinningPosition(Case c) { ///////Pas besoin car égal à testWin()
        return c.ord == this.game.height-1;
    }
*/
    public boolean isSafe(Case c) {
        return this.Lines.get(c.ord).isSafe(c);
    }

    public void update() {
        Iterator i = this.Lines.iterator();
        while(i.hasNext()) {
            Lane lane = (Lane)i.next();
            lane.update();
        }
    }


}
