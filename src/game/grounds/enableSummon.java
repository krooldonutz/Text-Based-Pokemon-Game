package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.PokeItems.PokeBall;
import game.actions.GohStatus;
import game.actions.SummonAction;
import game.actors.Goh;
import game.actors.Player;
import game.actors.Pokemon.Pokemon;
import game.utilities.ActorType;
import game.utilities.Status;
 /**
     * to enable summoning of pokemon in grounds and enabling the player to see Goh's status
     * @author smal0039
     */
public class enableSummon extends Ground {
    public enableSummon(char displayChar) {
        super(displayChar);
        //TODO Auto-generated constructor stub
    }
    public ActionList allowableActions(Actor actor, Location location, String direction){
		ActionList actions  = new ActionList();
        if (actor instanceof Player){
            if (actor.getInventory().size() > 0 ){
                for (int i = 0; i < actor.getInventory().size(); i++){
                    if (actor.getInventory().get(i) instanceof PokeBall && location.getActor() instanceof Player){
                        PokeBall pokeBall = (PokeBall)actor.getInventory().get(i);
                        actions.add(new SummonAction(pokeBall));
                    }
                }
            }
        }
        else if(actor.hasCapability(ActorType.POKEMON)){
            Pokemon pokemon = (Pokemon) actor;
            if (location.getActor() != null && location.getActor().hasCapability(ActorType.POKEMON)){
                pokemon.setLocation(location);
            }
        }
        if (location.getActor() != null && location.getActor().hasCapability(Status.IMMUNE)){
            actions.add(new GohStatus(Goh.getInstance()));
        }
        return actions;
	}

}
