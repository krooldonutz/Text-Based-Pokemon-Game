package game.PokeItems;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Pokemon.Pokemon;

public class PokeBall extends Item {
    private Pokemon pokemon;

    private int tickCount;

    public PokeBall(Pokemon pokemon) {
        super("Pokeball", '0', true);
        putPokemon(pokemon);
        this.addCapability(PokeType.POKEBALL);
        this.tickCount = 0;
    }

    public void putPokemon(Pokemon pokemon){
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon(){
        return this.pokemon;
    }

    public String toString(){
        return String.valueOf(pokemon);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        tickCount++;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        tickCount++;
    }
}
