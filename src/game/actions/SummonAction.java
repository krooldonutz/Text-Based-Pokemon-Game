package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.PokeItems.PokeBall;
import game.actors.Pokemon.Pokemon;

public class SummonAction extends Action{
    Pokemon pokemon;
    Pokemon otherPokemon;
    PokeBall pokeBall;

    public SummonAction(PokeBall pokeBall){
        this.pokemon = pokeBall.getPokemon();
        this.pokeBall = pokeBall;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        // TODO Auto-generated method stub
        Location actorLocation = map.locationOf(actor);
        
        if (map.at(actorLocation.x() - 1, actorLocation.y()).containsAnActor() == false){
                        map.at(actorLocation.x() - 1, actorLocation.y()).addActor(pokemon);
                        actor.removeItemFromInventory(pokeBall);
                        return "I SUMMON YOU! " + pokemon;
                    }
                
        return "Can't summon because there is a actor west of the trainer";
    }
    @Override
    public String menuDescription(Actor actor) {
        // TODO Auto-generated method stub
        return "Summon " + pokemon;
    }

    
    
}
