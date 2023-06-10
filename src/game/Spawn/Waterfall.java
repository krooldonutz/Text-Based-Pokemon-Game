package game.Spawn;

import game.PokeItems.Pokefruit;
import game.utilities.Element;
import game.actors.Pokemon.Squirtle;

import game.utilities.Utils;
/**
 * @author smal039
 */
public class Waterfall extends SpawningGround{
    public Waterfall(){
        super('W');
        setElement(Element.WATER);
        setSpawnAbility(getSpawnAbility());
        canSpawnSquirtle();
        setSpawnRequirement(2);
    }

    public int generateProbability(int min,int max){
        Utils probability = new Utils();
        return probability.nextID(min, max);
    }

    /**
     * If the probability of spawning a Squirtle is less than or equal to 20%, then push a Squirtle to the ArrayList
     * and return true, otherwise return false.
     *
     * @return A boolean value.
     */
    public boolean canSpawnSquirtle(){
        boolean probability= generateProbability(0,100) <= 20;
        if (probability){
            setPokemons(new Squirtle());
            return true;
        }
        return false;
    }
    /**
     * It generates a random number between 0 and 100 and if the number is less than or equal to 20, it
     * pushes a new WaterPokefruit into the arraylist.
     *
     * @return A boolean value.
     */
    public boolean canSpawnPokefruit(){
        boolean probability= generateProbability(0,100) <= 20;
        if (probability){
            pushPokeFruits(new Pokefruit(Element.WATER));
            return true;
        }
        return false;
    }

}
