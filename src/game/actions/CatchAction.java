package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.PokeItems.Candy;
import game.PokeItems.PokeBall;
import game.actors.Pokemon.Pokemon;
import game.utilities.Status;
/**
 * @author: smal0039
 * catching pokemon with a pokeball
 */
public class CatchAction extends Action {
    private Pokemon target;
    protected String direction;

    public CatchAction(Pokemon targetActor, String direction) {
        this.target = targetActor;
        this.direction = direction;
        
    }
    /**
     * @author: smal0039
     * catching pokemon with a pokeball
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.hasCapability(Status.CATCHABLE)){
        actor.addItemToInventory(new PokeBall(target));
        if (map.locationOf(target) != null){
            Location targetLoc = map.locationOf(target);
        map.removeActor(target);
        targetLoc.addItem(new Candy());
        }
        return actor + " captured a "+ target;
    }
    else{
        AffectionManager.getInstance().decreaseAffection(actor, target, 0);
    }
    return "unsucessful catch";
}

    @Override
    public String menuDescription(Actor actor) {
        return actor + " catches " + target; //+ " at " + direction;
    }
}