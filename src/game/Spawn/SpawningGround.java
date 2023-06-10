package game.Spawn;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Element;
import game.utilities.Status;
import game.PokeItems.Pokefruit;
import game.actors.Pokemon.Pokemon;
import java.util.ArrayList;
/**
 * @author: smal0039
 */
public abstract class SpawningGround extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */

    private boolean spawnAbility = true;
    private Location groundLocation;
    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private ArrayList<Pokefruit> pokefruits = new ArrayList<>();
    private ArrayList<Ground> surroundingGrounds = new ArrayList<>();
    private int spawnRequirement;


    public SpawningGround(char displayChar) {
        super(displayChar);
    }
    public void setSpawnAbility(boolean input){
        this.spawnAbility = input;
    }
    public boolean getSpawnAbility(){
        return this.spawnAbility;
    }
    public void setPokemons(Pokemon pokemon){
        pokemon.addCapability(Status.HOSTILE);
        this.pokemons.add(pokemon);
    }
    public void setElement(Element element){this.addCapability(element);
    }
    public void setSpawnRequirement(int spawnRequirement){this.spawnRequirement = spawnRequirement;}
    public int getSpawnRequirement(){return this.spawnRequirement;}
    public void pushPokeFruits(Pokefruit pokeFruit){
        this.pokefruits.add(pokeFruit);
    }

    public ArrayList<Pokefruit> getPokefruits(){
        return this.pokefruits;
    }

    public Pokefruit getPokefruitFirstIndex(){
        return this.pokefruits.get(0);
    }


    public Location getGroundLocation(){return this.groundLocation;}

    public Pokemon getPokemonsFirstIndex() {
        return pokemons.get(0);
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public abstract int generateProbability(int min, int max);

    public boolean isTherePokemon(){return this.pokemons.size() >= 1;};
    public boolean isTherePokefruits(){return this.pokefruits.size() >= 1;}

    /**
     * @author: smal0039
     *Putting the items and actors out from the spawning ground
     */
    public void tick(Location at) {
        super.tick(at);
        Location location = at;
        this.groundLocation = location;
        gatherSurrounding(location);

        if(isTherePokemon() && compatibleSurrounding() >= getSpawnRequirement()) {
            location.map().at(location.x() + 1, location.y() + 1).addActor(getPokemonsFirstIndex());
            pokemons.remove(0);
        }
        if (isTherePokefruits()){
            location.map().at(location.x() - 1, location.y() + 1).addItem(getPokefruitFirstIndex());
            pokefruits.remove(0);
        }
    }
    /**
     * @author: smal0039
     * counts the compatible surrounding ground for spawning
     */
    public int compatibleSurrounding(){
        int count = 0;

        for(Ground i: surroundingGrounds){{
            if (i.capabilitiesList().size() >0)
                if (i.hasCapability(i.capabilitiesList().get(0))){
                    count++;
                }}
        }
        return count;
    }

    /**
     * @author: smal0039
     * gathers the compatible surrounding ground for spawning
     */
    public void gatherSurrounding(Location location){
        for(int x = -2; x < 3; x++){
            for(int y = 0; y < 3; y++){
                if (x != 0 && y!= 0 ){
                    if (location.x()+x < 59 && location.x() + x >= 0 && location.y() + y < 13 && location.y()+ y >= 0)
                        surroundingGrounds.add(location.map().at(location.x() + x, location.y()+y).getGround());
                }
            }
        }
    }
}
