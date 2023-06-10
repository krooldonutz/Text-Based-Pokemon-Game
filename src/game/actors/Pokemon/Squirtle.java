package game.actors.Pokemon;


import edu.monash.fit2099.engine.actions.ActionList;

import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Weapons.Bubble;
import game.actions.DeathAction;
import game.utilities.Element;
import game.utilities.Status;

import game.behaviours.WanderBehaviour;

/**
 * @author ceci0001
 */
public class Squirtle extends Pokemon {

    /**
     * Constructor
     */
    public Squirtle(){
        super("Squirtle", 's', 100);
        this.addCapability(Element.WATER);
        this.addCapability(Status.CATCHABLE);
        getBehaviours().put(10, new WanderBehaviour());
        super.storeWeapon(generateId(), new Bubble());
    }
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (this.getLocation().getGround().hasCapability(Element.WATER) || otherActor.hasCapability(Element.FIRE)){
            toggleWeapon(true);
        }
        else{
            toggleWeapon(false);
        }
        return actions;
    }

    /**
     *
     * remove instance of Squirtle once dead
     */
    public void cleanDeath() {
        super.cleanDeath();
    }

    /**
     *
     * @return the intrinsic attack of squirtle
     */
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }

    /**
     *
     * @param weaponId the id of the weapon
     * @param weaponItem the special attack of squirtle
     */
    @Override
    protected void storeWeapon(int weaponId, WeaponItem weaponItem) {
        super.storeWeapon(generateId(), new Bubble());

    }

    /**
     * @param isEquipping FIXME: develop a logic to toggle weapon (put a selected weapon to the inventory - used!);
     */
    public void toggleWeapon(boolean isEquipping) {
        super.toggleWeapon(isEquipping);
    }

    /**
     * Squirtle will be hurt by 10 hit points.
     */
    @Override
    public void dayEffect() {
        this.hurt(10);
        if (!this.isConscious()) {
            cleanDeath();
            new DeathAction();
        }
    }

    /**
     * Squirtle will be healed by 10 hit points.
     */
    @Override
    public void nightEffect() {
        this.heal(10);
    }

    /**
     *
     * @return name, health and affection point of squirtle
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
