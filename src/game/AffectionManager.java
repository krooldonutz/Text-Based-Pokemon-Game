package game;

import edu.monash.fit2099.engine.actors.Actor;

import game.actors.Pokemon.Pokemon;

import game.utilities.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Affection Manager
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by:
 * smal0039
 */
public class AffectionManager {

    /**
     * Singleton instance (the one and only for a whole game).
     */
    private static AffectionManager instance;
    /**
     * HINT: is it just for a Charmander?
     */
    private final Map<Pokemon, Integer> affectionPoints;

    /**
     * We assume there's only one trainer in this manager.
     * Think about how will you extend it.
     */
    private Map<Actor, Map<Pokemon, Integer>> trainers = new HashMap<>();

    /**
     * private singleton constructor
     */
    private AffectionManager() {
        this.affectionPoints = new HashMap<>();
    }

    /**
     * Access single instance publicly
     *
     * @return this instance
     */
    public static AffectionManager getInstance() {
        if (instance == null) {
            instance = new AffectionManager();
        }
        return instance;
    }

    
    /**
     * This function adds the trainer to the list of trainers that this Pokemon knows about.
     * 
     * @param trainer The trainer to register.
     */
    public void registerTrainer(Actor trainer) {
        trainers.put(trainer, affectionPoints);
    }

    /**
     * Add Pokemon to the collection. By default, it has 0 affection point. Ideally, you'll register all instantiated Pokemon
     *
     * @param pokemon
     */
    public void registerPokemon(Pokemon pokemon) {
        affectionPoints.put(pokemon, 0);
    }

    /**
     * Get the affection point by using the pokemon instance as the key.
     *
     * @param pokemon Pokemon instance
     * @return integer of affection point.
     */
    public int getAffectionPoint(Pokemon pokemon) {
        return affectionPoints.get(pokemon);
    }

    public int getAffectionPointOfActor(Actor actor, Pokemon pokemon){
        return this.trainers.get(actor).get(pokemon);
    }

    public Map<Pokemon,Integer> getMapOfPokemonFromActor(Actor actor){
        return this.trainers.get(actor);
    }

    /**
     * Useful method to search a pokemon by using Actor instance.
     *
     * @param actor general actor instance
     * @return the Pokemon instance.
     */
    private Pokemon findPokemon(Actor actor) {
        for (Pokemon pokemon : affectionPoints.keySet()) {
            if (pokemon.equals(actor)) {
                return pokemon;
            }
        }
        return null;
    }

   
    /**
     * This function increases the affection point of a pokemon by a certain amount
     * 
     * @param player the player who is interacting with the pokemon
     * @param pokemon the pokemon that you want to increase affection for
     * @param point the amount of affection to increase by
     * @return The affection point of the pokemon
     */
    public String increaseAffection(Actor player ,Pokemon pokemon, int point) {
        if (pokemon.isConscious()){
            this.trainers.get(player).put(pokemon, getAffectionPointOfActor(player, pokemon) + point);
        //affectionPoints.put(pokemon, affectionPoints.get(pokemon) + point);
        if(getAffectionPointOfActor(player, pokemon) >= 50){
            pokemon.addCapability(Status.CATCHABLE);
        }
    
        }
        else{
            this.trainers.remove(pokemon);
        }
        return String.format("%s 's affection is increased by %d", pokemon, point);
    }

    /**
     * Decrease the affection level of the . Work on both cases when it is
     *
     * @param pokemon Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */
    public String decreaseAffection(Actor player, Pokemon pokemon, int point) {
        if (pokemon.isConscious()){
            this.trainers.get(player).put(pokemon, getAffectionPointOfActor(player, pokemon) - point);
        if(getAffectionPointOfActor(player, pokemon) <= -50){
            pokemon.addCapability(Status.DISLIKED);
        }
        }
        return String.format("%s 's affection is decreased by %d", pokemon, point);
    }

}
