package game.grounds;

import game.utilities.Element;


/**
 * @author ceci0001
 */
public class Hay extends enableSummon {


    /**
     * Constructor.
     *
     */
    public Hay() {
        super(',');
        this.addCapability(Element.GRASS);
    }

}
