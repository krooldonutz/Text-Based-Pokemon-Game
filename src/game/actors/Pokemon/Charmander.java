package game.actors.Pokemon;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.AffectionManager;
import game.Weapons.Ember;
import game.actions.DeathAction;
import game.actions.EvolveAction;

import game.behaviours.WanderBehaviour;
import game.utilities.ActorType;
import game.utilities.Element;
import game.utilities.Status;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: smal0039
 * 
 */
public class Charmander extends Pokemon {

    /**
     * Constructor.
     */
    public Charmander() {
        super("Charmander", 'c', 100);
        // HINT: add more relevant behaviours here
        this.addCapability(Element.FIRE);
        this.addCapability(Status.CATCHABLE);
        getBehaviours().put(10, new WanderBehaviour());
        super.storeWeapon(generateId(), new Ember());
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (this.getLocation().getGround().hasCapability(Element.FIRE)){
            toggleWeapon(true);
        }
        else{
            toggleWeapon(false);
        }
        if (AffectionManager.getInstance().getAffectionPoint(this) >= 100 && otherActor.hasCapability(ActorType.PLAYER)){
            actions.add(new EvolveAction(new Charmeleon(), this));
        }
        return actions;
    }
    /**
     * remove instance of Charmander once dead
     */
    public void cleanDeath() {
        super.cleanDeath();
    }

    /**
     *
     * @return the intrinsic attack of charmander
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }

    /**
     *
     * @param weaponId the id of the weapon
     * @param weaponItem the special attack of charmander
     */
    @Override
    protected void storeWeapon(int weaponId, WeaponItem weaponItem) {
        super.storeWeapon(generateId(), new Ember());
    }

    /**
     * Charmander will be healed by 10 hit points
     */
    @Override
    public void dayEffect() {
        this.heal(10);
        if (!this.isConscious()) {
            cleanDeath();
            new DeathAction();
        }
    }

    /**
     * Charmander will be hurt by 10 hit points
     */
    @Override
    public void nightEffect() {
        this.hurt(10);
        if (!this.isConscious()) {
            cleanDeath();
            new DeathAction();
        }
    }

    /**
     * @param isEquipping FIXME: develop a logic to toggle weapon (put a selected weapon to the inventory - used!);
     */
    public void toggleWeapon(boolean isEquipping) {
        super.toggleWeapon(isEquipping);
    }

    /**
     *
     * @return name, health and affection point of charmander
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
