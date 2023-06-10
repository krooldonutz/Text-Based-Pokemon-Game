package game.PokeItems;


import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.utilities.Element;

public class Pokefruit extends Item {

    private int tickCount;
    /***
     * Constructor.
     * @param element Element of the PokeFruit
     */
    public Pokefruit(Element element) {
        super(getPokeFruitName(element),'f',true);
        this.addCapability(element);
        this.addCapability(PokeType.POKEFRUIT);
        this.tickCount = 0;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        tickCount++;
    }

    /***
     * Gets the PokeFruit name from the element
     * @param element
     * @return Returns the Element PokeFruit name
     */
    public static String getPokeFruitName(Element element){

        switch (element){
            case FIRE:
                return "Fire PokeFruit";
            case WATER:
                return "Water PokeFruit";
            case GRASS:
                return  "Grass PokeFruit";
        }
        return "No element is given, so no PokeFruit is returned";
    }

//    @Override
//    public void tick(Location currentLocation, Actor actor) {
//        super.tick(currentLocation, actor);
//        tickCount++;
//    }
}
