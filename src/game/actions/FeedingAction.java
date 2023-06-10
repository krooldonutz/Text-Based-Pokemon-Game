package game.actions;

import java.util.ArrayList;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.PokeItems.PokeType;
import game.PokeItems.Pokefruit;
import game.actors.Pokemon.Pokemon;
import game.utilities.Element;

public class FeedingAction extends Action {

    protected Pokemon target;

    protected String direction;
    /**
     * @author: smal0039
     * feeds the pokemon with the appropriate pokefruit
     */
    public FeedingAction(Pokemon actor, String direction){
        this.target = actor;
        this.direction = direction;
    }
    /**
     * @author: smal0039
     * Checking if the player has a pokefruit in their inventory and if they do, it checks if the
    pokefruit is the same type as the pokemon. If it is, it increases the affection by 20, if not,
    it decreases the affection by 10.
     */
    @Override

    public String execute(Actor player, GameMap map) {
        ArrayList<Pokefruit> pokefruits = new ArrayList<>();
        for(int i = 0; i < player.getInventory().size(); i++){
            if(player.getInventory().get(i).hasCapability(PokeType.POKEFRUIT)){
                pokefruits.add((Pokefruit)player.getInventory().get(i));
            }
        }

        for(int i = 0; i < pokefruits.size(); i++){
            if(pokefruits.get(i).hasCapability(target.capabilitiesList().get(0))){
                player.removeItemFromInventory(pokefruits.get(i));
                return AffectionManager.getInstance().increaseAffection(player,target, 20);
            }
        }
        if (pokefruits.size() > 0){
            player.removeItemFromInventory(pokefruits.get(0));
            return AffectionManager.getInstance().decreaseAffection(player, target, 10);
        }

        return "No pokefruits to feed with";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target + " at " + direction;
    }

}
