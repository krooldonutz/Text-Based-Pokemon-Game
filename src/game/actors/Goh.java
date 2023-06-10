package game.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;

import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.PokeItems.PokeType;
import game.PokeItems.Pokefruit;
import game.actions.CatchAction;


import game.actors.Pokemon.Pokemon;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.utilities.ActorType;

import game.utilities.Status;
/**
     * @author: smal0039
     * class to represent Goh
     */
public class Goh extends Actor{

    /**
     * hashmap of the behaviours of Goh
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private  static Goh instance;
    private ActionList lActionList = new ActionList();
    public Goh() {
        super("Goh", 'G', 100);
        //TODO Auto-generated constructor stub
        getBehaviours().put(10, new WanderBehaviour());
        this.addCapability(Status.ROBOT);
        this.addCapability(ActorType.GOH);
        AffectionManager.getInstance().registerTrainer(this);
    }

    /**
     * If the instance is null, create a new instance and return it. If the instance is not null,
     * return the existing instance, this also makes sure there is only one Goh in the entire game
     * 
     * @return The instance of the Goh class.
     */
    public static Goh getInstance() {
        if (instance == null) {
            instance = new Goh();
        }
        return instance;
    }

    /**
     *
     * @return hasmap of the behaviours of the pokemon
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * This function returns a list of actions that the player can perform on the actor in the given
     * direction
     * 
     * @param otherActor The actor that the player is interacting with.
     * @param direction the direction the player is facing
     * @param map The map that the actor is on.
     * @return The list of actions that the player can perform on the actor.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.CATCHABLE)){
            ArrayList<Pokefruit> pokefruits = new ArrayList<>();
            for(int i = 0; i < this.getInventory().size(); i++){
                if(this.getInventory().get(i).hasCapability(PokeType.POKEFRUIT)){
                    pokefruits.add((Pokefruit)this.getInventory().get(i));
                }
            }
            if (pokefruits.size() > 0){
                this.removeItemFromInventory(pokefruits.get(0));
                AffectionManager.getInstance().increaseAffection(this,(Pokemon)otherActor, 20);
            }
            if (AffectionManager.getInstance().getAffectionPointOfActor(this, (Pokemon)otherActor) >= 50){
                this.lActionList.add(new CatchAction((Pokemon) otherActor, direction));
            }
        } 
		return actions;
	} 
    
    @Override
    /**
     * This function is the playTurn function of the Goh class. It is used to determine the action that
     * the Goh will perform in each turn.
     * 
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.isConscious()){ 
        if (map.locationOf(this).getItems().size() > 0){
            for (int i = 0; i < map.locationOf(this).getItems().size(); i++){
                new PickUpItemAction(map.locationOf(this).getItems().get(i)).execute(this, map);
            }
        }
        if (lActionList.size() > 0){
           ActionList temp = lActionList;
            for(Action action: temp){
                action.execute(this, map);
            }
        }
        lActionList.clear();
        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }}
        return new DoNothingAction();
    
    }

    public String getHp(){
        return printHp();
    }
    
}
