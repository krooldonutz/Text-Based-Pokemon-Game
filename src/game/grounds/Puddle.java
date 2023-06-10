package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Element;
import game.utilities.Utils;
import game.time.TimePerception;

/**
 * @author ceci0001
 */
public class Puddle extends enableSummon implements TimePerception {

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
     *
     */
    public Puddle() {
        super('~');
        this.addCapability(Element.WATER);
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
     *
     * @param currentLocation The location of the Ground
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        this.location = currentLocation;
        tickCount++;

    }


    /**
     * Puddle has 10% chance of being destroyed (converted to dirt)
     */
    @Override
    public void dayEffect() {
        if (location != null) {
            boolean expand = generateProbability(0, 10) == 1;

            for (Exit exit : location.getExits()) {
                if (expand && exit.getDestination().getGround() instanceof Puddle) {
                    {
                        exit.getDestination().setGround(new Dirt());
                    }
                }
            }
        }
    }

    /**
     * Puddle has 10% chance to expand (convert its surrounding grounds to Puddle)
     */
    @Override
    public void nightEffect() {
        if (location != null) {
            boolean expand = generateProbability(0, 10) == 1;

            for (Exit exit : location.getExits()) {
                if (expand && !(exit.getDestination().getGround() instanceof Floor) && !(exit.getDestination().getGround() instanceof Wall) && !(exit.getDestination().getGround().hasCapability(Element.WATER))) {
                    {
                        exit.getDestination().setGround(new Puddle());
                    }
                }
            }
        }
    }
}
