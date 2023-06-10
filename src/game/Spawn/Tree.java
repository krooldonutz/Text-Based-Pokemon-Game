package game.Spawn;



import game.PokeItems.Candy;
import game.PokeItems.Pokefruit;
import game.actors.Pokemon.Bulbasaur;
import game.grounds.Wall;
import game.utilities.Element;
import game.utilities.Utils;
import game.grounds.Floor;
import game.grounds.Hay;
import game.time.TimePerception;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;


/**
 * @author: smal0039, ceci0001
 */
public class Tree extends SpawningGround implements TimePerception{
    static final int MIN_INT = 0;
    static final int MAX_INT = 100;

    /**
     * tick counter
     */
    private int tickCount;

    /**
     * to store current location of lava
     */
    private Location location;

    public Tree() {
        super('T');
        setSpawnAbility(getSpawnAbility());
        canSpawnBulbasaur();
        canSpawnPokefruit();
        setElement(Element.GRASS);
        this.tickCount = 0;
    }
    /**
     * @author: smal0039
     */
    public int generateProbability(int min,int max){
        Utils probability = new Utils();
        return probability.nextID(min, max);
    }


    /**
     * If the random number generated is less than or equal to 15, then the function returns true and a
     * Bulbasaur is added to the list of Pokemons
     * @author smal0039
     * @return A boolean value.
     */
    public boolean canSpawnBulbasaur(){
        int probability = generateProbability(MIN_INT, MAX_INT);
        if (probability <= 15){
            setPokemons(new Bulbasaur());
            return true;
        }
        else{return false;}
    }


    /**
     * If the random number generated is less than or equal to 15, then a GrassPokefruit is pushed into
     * the Pokefruit array
     *
     * @return A boolean value.
     */
    public boolean canSpawnPokefruit(){
        int probability = generateProbability(MIN_INT, MAX_INT);
        if (probability <= 15){
            pushPokeFruits(new Pokefruit(Element.GRASS));
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     * @param currentLocation The location of the Ground
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        this.location = currentLocation;
        tickCount++;
    }

    /**
     * Trees have 5% chance of dropping a Candy
     */
    @Override
    public void dayEffect() {
        if (location != null) {
            boolean expand = generateProbability(0, 20) == 1;
            int area = generateProbability(0, 8);

            if (expand) {
                location.getExits().get(area).getDestination().addItem(new Candy());
            }
        }
    }

    /**
     * Trees have 10% chance to expand (convert its surroundings to
     * either all Trees or all Hays randomly)
     */
    @Override
    public void nightEffect() {
        if (location != null) {
            boolean expand = generateProbability(0, 10) == 1;
            int treeOrHay = generateProbability(0, 10);

            for (Exit exit : location.getExits()) {
                if (expand && !(exit.getDestination().getGround() instanceof Floor) && !(exit.getDestination().getGround() instanceof Wall) && !(exit.getDestination().getGround().hasCapability(Element.GRASS))) {
                    {
                        if (treeOrHay <= 5) {
                            exit.getDestination().setGround(new Hay());
                        }
                        else {
                            exit.getDestination().setGround(new Tree());
                        }
                    }
                }
            }
        }
    }

}
