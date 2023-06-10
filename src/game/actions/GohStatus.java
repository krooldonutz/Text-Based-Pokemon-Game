package game.actions;

import java.util.Map;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.actors.Pokemon.Pokemon;
/**
     * Created By: 
     * @author: smal0039
     * action to show Goh's status
     */
public class GohStatus extends Action {
    private Actor goh;
    
    public GohStatus(Actor goh){
        this.goh = goh;
    }
    @Override
    // Showing the status of Goh.
    public String execute(Actor actor, GameMap map) {
        // TODO Auto-generated method stub
        String ret = this.goh +" |"+ map.locationOf(this.goh).x()+", " +map.locationOf(this.goh).y()+ "|" 
                    + this.goh.getInventory().toString();
        ret += "\n";
        Map<Pokemon, Integer> pokemons = AffectionManager.getInstance().getMapOfPokemonFromActor(actor);
        for (Pokemon pokemon: pokemons.keySet()){
            if (pokemon.isConscious() && map.contains(actor)){
                ret += pokemon.getName() +" "+pokemon.getHp()+" AP: " + AffectionManager.getInstance().getAffectionPointOfActor(actor, pokemon);
                ret += "\n";
            }
        }
        return ret;
    }


    @Override
    public String menuDescription(Actor actor) {
        // TODO Auto-generated method stub
        return "Show Goh's status ";
    }
    
}
