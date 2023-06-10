package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Pokemon.Pokemon;

public class EvolveAction extends Action{

    private Pokemon evolve;
    private Pokemon target;

    // This is the constructor of the class. It is used to create an instance of the class.
    public EvolveAction(Pokemon evolve, Pokemon target){
        this.evolve = evolve;
        this.target =  target;
    }

    @Override
    // Removing the target pokemon and adding the evolved pokemon to the map.
    public String execute(Actor actor, GameMap map) {
        // TODO Auto-generated method stub
        Location location = map.locationOf(target);
        map.removeActor(target);
        map.addActor(evolve, location);
        return target + " is evolved into " + evolve;
    }

    @Override
    public String menuDescription(Actor actor) {
        // TODO Auto-generated method stub
        return "Evolve " + target + " to " + evolve;
    }
    
}
