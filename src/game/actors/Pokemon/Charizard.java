package game.actors.Pokemon;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import game.Weapons.Blaze;

import game.Weapons.FireSpin;
import game.behaviours.WanderBehaviour;
import game.grounds.Fire;
import game.utilities.Element;


/**
 * Created by:
 * @author smal0039
 * Modified by: smal0039
 * Class for Charizard Pokemon
 */
public class Charizard extends Pokemon{
    
    public Charizard(){
        super("Charizard", 'Z', 250);
        this.addCapability(Element.FIRE);
        this.addCapability(Element.DRAGON);
        getBehaviours().put(10, new WanderBehaviour());
        super.storeWeapon(generateId(), new FireSpin());
        super.storeWeapon(generateId(), new Blaze());
    }

    public void toggleWeapon(boolean isEquipping) {
        super.toggleWeapon(isEquipping);

    }

    /**
     * If the ground is on fire, then the Charizard will use Fire Spin, which will set the ground on
     * fire in all the exits of the current location
     * 
     * @param otherActor The actor that is trying to interact with this actor.
     * @param direction The direction the actor is moving in.
     * @param map The map that the actor is on.
     * @return The actions that the actor can perform.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        super.allowableActions(otherActor, direction, map);
        ActionList actions = new ActionList();
        if (this.getLocation().getGround().hasCapability(Element.FIRE)){
            toggleWeapon(true);
            if (this.getWeapon().verb() == "fire spins"){
                Location charizardLocation = map.locationOf(this);
                for (Exit exit : charizardLocation.getExits()) {
                    exit.getDestination().setGround(new Fire());
                }
            }
        }
        else{
            toggleWeapon(false);
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
        super.storeWeapon(generateId(), new FireSpin());
    }

}
