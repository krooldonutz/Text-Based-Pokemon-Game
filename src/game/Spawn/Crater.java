package game.Spawn;



import game.PokeItems.Pokefruit;
import game.utilities.Element;

import game.actors.Pokemon.Charmander;


import game.utilities.Utils;


public class Crater extends SpawningGround {
    /**
     * @author: smal0039
     */
    public Crater() {
        super('O');
        setSpawnAbility(getSpawnAbility());
        spawnCharmanderAndOrPokefruit();
        setElement(Element.FIRE);
        setSpawnRequirement(0);
    }


    public int generateProbability(int min, int max) {
        Utils u = new Utils();
        return u.nextID(min, max);
    }


    /**
     * If the probability of generating a number between 0 and 10 is 10, then spawn a Charmander. If
     * the probability of generating a number between 0 and 4 is 0, then spawn a FirePokefruit
     */
    public void spawnCharmanderAndOrPokefruit() {
        boolean canSpawnCharmander = generateProbability(0, 10) == 10;
        boolean canSpawnPokefruit = generateProbability(0, 4) == 0;
        if (canSpawnCharmander) {

            setPokemons(new Charmander());
        }
        if (canSpawnPokefruit) {
            pushPokeFruits(new Pokefruit(Element.FIRE));
        }
    }

}



