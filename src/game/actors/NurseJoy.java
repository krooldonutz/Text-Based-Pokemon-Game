package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradeAction;

/**
 * Class representing NurseJoy.
 *
 * Created by:
 * @author Jobin Mathew Dan
 * Modified by: 01/10/2022
 * jdan0036
 */

public class NurseJoy extends Actor{


    public NurseJoy(){
        super("Nurse Joy", '%', 1);
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        return new ActionList(new TradeAction());
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        return new DoNothingAction();
    };

}

