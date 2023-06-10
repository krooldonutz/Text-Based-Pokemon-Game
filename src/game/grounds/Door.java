package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;

/**
 * @author ceci0001
 * A Door class that creates a door that acts as a telporter for a player (the door being one of the exits
 * for the player).
 */
public class Door extends Ground {

    /**
     * location of target
     */
    private Location target;

    /**
     * the map in which the player is currently in
     */
    private String currentMap;

    /**
     * the map in which the player is going to teleport
     */
    private String targetMap;

    /**
     * Constructor.
     */
    public Door() {
        super('=');
    }

    /**
     * Constructor overloading.
     * @param target location of target
     * @param currentMap the map in which the player is currently in
     * @param targetMap the map in which the player is going to teleport to
     */
    public Door(Location target, String currentMap, String targetMap) {
        super('=');
        this.target = target;
        this.currentMap = currentMap;
        this.targetMap = targetMap;
    }

    /**
     * Getter method to get the target location
     * @return location of target
     */
    public Location getTarget() {
        return target;
    }

    /**
     * Getter method to get the current map
     * @return the map in which the player is currently in
     */
    public String getCurrentMap() {
        return currentMap;
    }

    /**
     * Getter method to get target map
     * @return the map in which the player is going to teleport to
     */
    public String getTargetMap() {
        return targetMap;
    }

    /**
     * The player cannot stand on top of the door
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * The method will check all 4 main directions of the player (North, East, West, South) for a door, as it will be weird to enter
     * a door from the player's NorthEast, NorthWest, SouthEast, SouthWest directions. When it finds a door, it will teleport the player
     * from its current map to the door of the target map.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the list of actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();

        for (Exit exit : location.getExits()) {
            if ((exit.getName().equals("North") || exit.getName().equals("South") || exit.getName().equals("East") || exit.getName().equals("West")) && exit.getDestination().containsAnActor()) {
                actionList.add(new TeleportAction(location, new Door(target, currentMap, targetMap)));
            }
        }
        return actionList;
    }

}
