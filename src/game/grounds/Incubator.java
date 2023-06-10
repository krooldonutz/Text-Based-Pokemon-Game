package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.PokeItems.PokeEgg;
import game.PokeItems.PokeType;

/**
 * Class representing Incubator Item.
 *
 * Created by:
 * @author Jobin Mathew Dan
 * Modified by: 16/10/2022
 * jdan0036
 */
public class Incubator extends Ground{

    /**
     * Constructor for Incubator
     */
    public Incubator(){
        super('X');
        this.addCapability(PokeType.INCUBATOR);
    }

}
