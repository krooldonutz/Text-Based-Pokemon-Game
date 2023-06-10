package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

import game.utilities.Element;
import game.utilities.ElementsHelper;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class AttackBehaviour implements Behaviour {

    private final String direction;

    private final Actor target;

    private final Actor attacker;
    /**
     *  HINT: develop a logic to check surrounding, check elements, and return an action to attack that opponent.
     */

    public AttackBehaviour(String direction,Actor attacker ,Actor target) {
        this.direction = direction;
        this.target = target;
        this.attacker = attacker;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
       // FIXME: fakeOtherActor is a completely new instance that doesn't exist anywhere in the map! Check the requirement.
        if(!map.contains(target) || !map.contains(actor)) {
            return null;
        }
 


        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getActor() == target && ElementsHelper.hasAnySimilarElements(actor, target.findCapabilitiesByType(Element.class))) {
                return new AttackAction(target, attacker,direction);
            }
        }
        return null;
    }
}
