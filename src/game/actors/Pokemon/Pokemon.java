package game.actors.Pokemon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.AffectionManager;

import game.Weapons.BackupWeapons;
import game.actions.AttackAction;
import game.actions.CatchAction;

import game.actions.FeedingAction;

import game.actors.Player;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.time.TimePerception;
import game.time.TimePerceptionManager;
import game.utilities.ActorType;
import game.utilities.Element;
import game.utilities.ElementsHelper;

import game.utilities.Utils;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ceci0001, smal0039
 */
public abstract class Pokemon extends Actor implements TimePerception {

    /**
     * hashmap of the behaviours of the pokemon
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * initialize a hasmap to store special attack of pokemon
     */
    private final BackupWeapons backupWeapons;

    /**
     * the min range
     */
    private final int LOW = 100;

    /**
     * the max range
     */
    private final int HIGH = 999;

    private Location location;
    private int hp;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Pokemon(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.hp = hitPoints;
        this.backupWeapons = new BackupWeapons();
        this.registerInstance();
        AffectionManager.getInstance().registerPokemon(this);
        this.addCapability(ActorType.POKEMON);
    }

    /**
     * This function updates the hp of the Pokemon.
     * 
     * @param hp The current health of the Pokemon.
     */
    public void updateHp(int hp){
        this.hp = hp;
    }
   /**
    * This function returns the name of the Pokemon
    * 
    * @return The name of the Pokemon.
    */
    public String getName(){
        return this.name;
    }

    /**
     * The function `getHp()` returns the value of the function `printHp()`
     * 
     * @return The return type is a String.
     */
    public String getHp(){
        return printHp();
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }

    /**
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (this.isConscious()){
        if (otherActor.hasCapability(ActorType.POKEMON)) {
            AttackAction temp = new AttackAction(otherActor, this,direction);
            temp.execute(otherActor, map);
            getBehaviours().put(2, new AttackBehaviour(direction, this, otherActor));
        }
        if (otherActor.hasCapability(ActorType.PLAYER)){
            actions.add(new CatchAction(this, direction));
            actions.add(new FeedingAction(this, direction));
            if(AffectionManager.getInstance().getAffectionPointOfActor(otherActor, this) >= 75){
                getBehaviours().put(2, new FollowBehaviour(otherActor));
            }
        }
        this.setLocation(map.locationOf(this));
    }
        return actions;
    }

    /**
     * effects that happen to the pokemon during day time
     */
    @Override
    public void dayEffect() {

    }   

    /**
     * effects that happen to the pokemon during night time
     */
    @Override
    public void nightEffect() {

    }

    /**
     *
     * @return hasmap of the behaviours of the pokemon
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * remove instance of Pokemon once dead
     */
    public void cleanDeath() {
        TimePerceptionManager.getInstance().cleanUp(this);
    }

    /**
     *
     * @param weaponId the id of the weapon
     * @param weaponItem the special attack of pokemon
     */
    protected void storeWeapon(int weaponId, WeaponItem weaponItem) {
        backupWeapons.registerSpecialAttack(weaponId, weaponItem);
    }

    /**
     *
     * @return random id to differentiate each special attack
     */
    public int generateId() {
        Utils utils = new Utils();
        return utils.nextID(LOW, HIGH);
    }

    /**
     * @param isEquipping FIXME: develop a logic to toggle weapon (put a selected weapon to the inventory - used!);
     */
    public void toggleWeapon(boolean isEquipping) {
        if (isEquipping) {
            //equip specialAttack
            for (int weaponId : backupWeapons.specialAttack.keySet()) {
                if (ElementsHelper.hasAnySimilarElements(backupWeapons.specialAttack.get(weaponId), this.findCapabilitiesByType(Element.class)))
                    System.out.println(backupWeapons.specialAttack.get(weaponId));
                    this.addItemToInventory(backupWeapons.specialAttack.get(weaponId));
            }
        } else {
            //unequipped specialAttack
            if (getInventory().size() > 0){
                List<Item> temp = new ArrayList<>(getInventory());
                for (Item item : temp) {
                    if (item.asWeapon() instanceof WeaponItem) {
                        this.removeItemFromInventory(item);
                    }
                }
            }

        }
    }
    /**
     * By using behaviour loops, it will decide what will be the next action automatically.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.isConscious()){
        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }}
        return new DoNothingAction();
    
    }

    /**
     *
     * @return name, health and affection point of pokemon
     */
    public String toString() {
        return name + printHp()+ " AP: "+AffectionManager.getInstance().getAffectionPointOfActor(Player.getInstance(), this);
    }
}
