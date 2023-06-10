package game.PokeItems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Candy extends Item {

    private int tickCount;

    public Candy() {
        super("Candy", '*', true);
        this.addCapability(PokeType.CANDY);
        this.tickCount = 0;

    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        tickCount++;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        tickCount++;
    }
}
