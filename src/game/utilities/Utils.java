package game.utilities;

import java.util.Random;

public class Utils {
    public int nextID(int low, int high) {
        Random r = new Random();
        return (r.nextInt(high - low) + low);
    }
}

