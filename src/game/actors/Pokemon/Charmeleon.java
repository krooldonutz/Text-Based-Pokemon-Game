package game.actors.Pokemon;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.AffectionManager;
import game.Weapons.Blaze;
import game.actions.EvolveAction;

import game.behaviours.WanderBehaviour;
import game.utilities.ActorType;
import game.utilities.Element;

/**
 * Created by:
 * @author smal0039
 * Modified by: smal0039
 * Class for Charmeleon Pokemon
 */

public class Charmeleon extends Pokemon{
    public Charmeleon(){
        super("Charmeleon", 'C', 150);
        this.addCapability(Element.FIRE);
        getBehaviours().put(10, new WanderBehaviour());
        super.storeWeapon(generateId(), new Blaze());
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
            actions.add(new EvolveAction(new Charizard(), this));
        }
        return actions;
    }
    
    /**
     *
     * @param weaponId the id of the weapon
     * @param weaponItem the special attack of charmander
     */
    @Override
    protected void storeWeapon(int weaponId, WeaponItem weaponItem) {
        super.storeWeapon(generateId(), new Blaze());
    }

        /**
     * @param isEquipping FIXME: develop a logic to toggle weapon (put a selected weapon to the inventory - used!);
     */
    public void toggleWeapon(boolean isEquipping) {
        super.toggleWeapon(isEquipping);
    }
    
     /**
     *
     * @return the intrinsic attack of charmander
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }

    public void cleanDeath() {
        super.cleanDeath();
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
