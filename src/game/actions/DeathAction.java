package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class DeathAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!actor.isConscious()) {
            map.removeActor(actor);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "has died";
    }
}
