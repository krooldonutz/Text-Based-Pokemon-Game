package game.actors;



import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.AffectionManager;
import game.PokeItems.PokeEgg;
import game.actions.GohStatus;

import game.time.TimePerceptionManager;
import game.utilities.ActorType;
import game.utilities.Element;
import game.utilities.Status;

/**
 * Class representing the Player.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * smal0039
 */
public class Player extends Actor {

	private final Menu menu = new Menu();

	private static Player instance;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.IMMUNE);
		this.addCapability(ActorType.PLAYER);
		AffectionManager.getInstance().registerTrainer(this);
	}

	/**
	 * If the instance variable is null, create a new Player object and assign it to the instance
	 * variable. Otherwise, return the instance variable
	 * this also makes sure there's only one player in the game
	 * 
	 * @return The instance of the Player class.
	 */
	public static Player getInstance() {
        if (instance == null) {
            instance = new Player("Ash", '@', 1);
        }
        return instance;
    }

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		TimePerceptionManager.getInstance().run();
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
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
		System.out.println(otherActor);
		actions.add(new GohStatus((otherActor)));
		return actions;
	}

	private Object getBehaviours() {
		return null;
	}

	@Override
	public char getDisplayChar() {
		return super.getDisplayChar();
	}
}
