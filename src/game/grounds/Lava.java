package game.grounds;

import edu.monash.fit2099.engine.positions.*;
import game.utilities.Element;
import game.utilities.Utils;
import game.time.TimePerception;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: ceci0001
 *
 */
public class Lava extends enableSummon implements TimePerception {

    /**
     * tick counter
     */
    private int tickCount;

    /**
     * to store current location of lava
     */
    private Location location;

    /**
     * Constructor.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
        this.registerInstance();
        this.tickCount = 0;
    }

    /**
     *
     * @param min the min range
     * @param max the max range
     * @return a random number between min and max
     */
    public int generateProbability(int min, int max){
        Utils utils = new Utils();
        return utils.nextID(min ,max);
    }

    /**
     * @param currentLocation The location of the Ground
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        this.location = currentLocation;
        tickCount++;
    }

    /**
     * Lava ground has 10% chance to expand (convert its surrounding to Lava).
     *
     */
    @Override
    public void dayEffect() {
        if (location != null) {
            boolean expand = generateProbability(0, 10) == 1;

            for (Exit exit : location.getExits()) {
                if (!exit.getDestination().containsAnActor() && expand && !(exit.getDestination().getGround() instanceof Floor) && !(exit.getDestination().getGround() instanceof Wall) && !(exit.getDestination().getGround().hasCapability(Element.FIRE))) {
                    {
                        exit.getDestination().setGround(new Lava());
                    }
                }
            }
        }
    }

    /**
     * Lava ground has 10% chance of being destroyed (converted to dirt)
     * as long as there is no actor on it.
     */
    @Override
    public void nightEffect() {
        if (location != null) {
            boolean expand = generateProbability(0, 10) == 1;

            for (Exit exit : location.getExits()) {
                if (!exit.getDestination().containsAnActor() && expand && exit.getDestination().getGround() instanceof Lava) {
                    {
                        exit.getDestination().setGround(new Dirt());
                    }
                }
            }
        }
    }
}
