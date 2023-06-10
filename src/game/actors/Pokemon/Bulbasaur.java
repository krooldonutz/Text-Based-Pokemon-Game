package game.actors.Pokemon;


import edu.monash.fit2099.engine.actions.ActionList;

import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Weapons.VineWhip;
import game.actions.DeathAction;
import game.utilities.Element;
import game.utilities.Status;

import game.behaviours.WanderBehaviour;

/**
 * @author ceci0001
 */
public class Bulbasaur extends Pokemon {

    /**
     * Constructor
     */
    public Bulbasaur() {
        super("Bulbasaur", 'b', 100);
        this.addCapability(Element.GRASS);
        this.addCapability(Status.CATCHABLE);
        getBehaviours().put(10, new WanderBehaviour());
        super.storeWeapon(generateId(), new VineWhip());
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (this.getLocation().getGround().hasCapability(Element.GRASS)){
            toggleWeapon(true);
        }
        else{
            toggleWeapon(false);
        }
        return actions;
    }

    /**
     *
     * remove instance of Bulbasaur once dead
     */
    public void cleanDeath() {
        super.cleanDeath();
    }

    /**
     *
     * @return the intrinsic attack of bulbasaur
     */
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }

    /**
     *
     * @param weaponId the id of the weapon
     * @param weaponItem the special attack of bulbasaur
     */
    @Override
    protected void storeWeapon(int weaponId, WeaponItem weaponItem) {
        super.storeWeapon(generateId(), new VineWhip());

    }

    /**
     * @param isEquipping FIXME: develop a logic to toggle weapon (put a selected weapon to the inventory - used!);
     */
    public void toggleWeapon(boolean isEquipping) {
        super.toggleWeapon(isEquipping);
    }

    /**
     * Bulbasaur will be hurt by 5 hit points.
     */
    @Override
    public void dayEffect() {
        this.hurt(5);
        if (!this.isConscious()) {
            cleanDeath();
            new DeathAction();
        }
    }

    /**
     * Bulbasaur will be healed by 5 points.
     */
    @Override
    public void nightEffect() {
        this.heal(5);
    }

    /**
     *
     * @return name, health and affection point of bulbasaur
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
