package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.time.TimePerception;
import game.utilities.Element;

public class Fire extends enableSummon implements TimePerception {
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
    public Fire() {
        super('v');
        this.addCapability(Element.FIRE);
        this.registerInstance();
        this.tickCount = 0;
    }

    /**
     * @param currentLocation The location of the Ground
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        this.location = currentLocation;
        tickCount++;

        if (tickCount >= 3){
            location.setGround(new Dirt());
        }
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                {
                    Actor actor = exit.getDestination().getActor();
                    if (!actor.hasCapability(Element.FIRE)){
                        actor.hurt(10);
                    }
                }
            }
        }
    }

    

    @Override
    public void dayEffect() {
        // TODO Auto-generated method stub
    }

    @Override
    public void nightEffect() {
        // TODO Auto-generated method stub
    }
    
}
