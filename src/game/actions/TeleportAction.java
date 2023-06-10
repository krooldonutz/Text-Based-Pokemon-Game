package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Door;

/**
 * @author ceci0001
 * TeleportAction class that allows an actor to teleport to and back from one map to another
 */
public class TeleportAction extends Action {

    /**
     * location of player
     */
    private final Location location;

    /**
     * the target door in which the player will teleport to
     */
    private final Door targetDoor;

    /**
     * Constructor.
     * @param location location of player
     * @param targetDoor the target door in which the player will teleport to
     */
    public TeleportAction(Location location, Door targetDoor) {
        this.location = location;
        this.targetDoor = targetDoor;
    }

    /**
     * When the TeleportAction is called, it will move the player to the target location of the door in another map, and it will set a
     * new door on the location the player is teleported to, which allows the player to teleport back to the previous map it was at.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the menuDescription string stating the map the player is currently in to the map where the player is going to teleport to
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.contains(actor)) {
            map.moveActor(actor, targetDoor.getTarget());
        }
        targetDoor.getTarget().setGround(new Door(location, targetDoor.getTargetMap(), targetDoor.getCurrentMap()));

        return menuDescription(actor);
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return a string stating the map the player is currently in to the map where the player is going to teleport to
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports from " + targetDoor.getCurrentMap() + " to " + targetDoor.getTargetMap();
    }
}
