package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Pokemon.Pokemon;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * smal0039
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    protected Actor attacker;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public AttackAction(Actor target,Actor attacker, String direction) {
        this.target = target;
        this.direction = direction;
        this.attacker = attacker;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = attacker.getWeapon();
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return attacker + " misses " + target + ".";
        }

        int damage = weapon.damage();
        target.hurt(damage);
        String result = attacker + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        if (!target.isConscious()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(attacker));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";
        }
        
        System.out.println(result);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return attacker + " attacks " + target + " at " + direction;
    }
}
