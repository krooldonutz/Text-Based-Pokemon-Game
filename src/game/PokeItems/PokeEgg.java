package game.PokeItems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;

import game.actors.Pokemon.Pokemon;
import game.actors.Pokemon.Charmander;
import game.actors.Pokemon.Squirtle;
import game.actors.Pokemon.Bulbasaur;

import game.utilities.Element;


import java.util.List;


/**
 * Class representing PokeEgg.
 *
 * Created by:
 * @author Jobin Mathew Dan
 * Modified by: 16/10/2022
 * jdan0036
 */
public class PokeEgg extends Item{
    private Location currentLocation;
    private int tick = 0;

    /**
     * Constructor of PokeEgg
     * @param element - Pokemon's element of the PokeEgg
     */
    public PokeEgg(Element element){
        super(getPokeEggName(element), 'g', true);
        this.addCapability(element);
        this.addCapability(PokeType.POKEEGG);
    }

    /**
     *
     * @param element - element of the Pokemon
     * @return Pokemon egg name
     */
    public static String getPokeEggName(Element element){

        switch (element){
            case FIRE:
                return "Charmander Egg";
            case WATER:
                return "Squirtle Egg";
            case GRASS:
                return  "Bulbasaur Egg";
        }
        return "No element is given, so no PokeEgg is returned";
    }

    public boolean checkIncubator(){
        if (currentLocation.getGround().hasCapability(PokeType.INCUBATOR)) {
            if(currentLocation.getItems().contains(this)) {
                tick++;
                System.out.println(tick);
                return true;
            }
        }
        return false;
    }

    /**
     * Method to hatch the Pokemon
     * @param spawnLocation
     */
    public void eggIncubate(Location spawnLocation){
        if (this.tick == 4 && this.hasCapability(Element.FIRE) ){
            Pokemon charmander = new Charmander();
            pokemonHatching(charmander, spawnLocation);
        }
        if (this.tick == 2 && this.hasCapability(Element.WATER)){
            Pokemon squirtle = new Squirtle();
            pokemonHatching(squirtle, spawnLocation);
        }
        if (this.tick == 3 && this.hasCapability(Element.GRASS)){
            Pokemon bulbasaur = new Bulbasaur();
            pokemonHatching(bulbasaur,spawnLocation);
        }
    }

    public void pokemonHatching(Pokemon pokemon, Location spawnLocation){
        if(currentLocation != null && !currentLocation.containsAnActor()) {
            spawnLocation.removeItem(this);
            spawnLocation.addActor(pokemon);
            tick = 0;
        }
        else if(currentLocation != null && currentLocation.containsAnActor()) {
            List<Exit> exits = currentLocation.getExits();
            for (Exit eachExit: exits){
                Location summon = eachExit.getDestination();
                if(summon.canActorEnter(pokemon)){
                    spawnLocation.removeItem(this);
                    summon.addActor(pokemon);
                    tick = 0;
                    break;
                }
            }
        }
    }

    @Override
    public void tick(Location currentLocation) {
        this.currentLocation = currentLocation;
        if (checkIncubator()) {
            eggIncubate(currentLocation);
        }

    }
}
