package game.time;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that gives time perception  on the affected instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: ceci0001
 *
 */
public class TimePerceptionManager{
    /**
     * A list of polymorph instances (any classes that implements TimePerception,
     * such as, a Charmander implements TimePerception, it will be stored in here)
     */
    private final List<TimePerception> timePerceptionList;

    private int turn;

    private TimePeriod shift; // DAY or NIGHT

    /**
     * A singleton instance
     */
    private static TimePerceptionManager instance;

    /**
     * Get the singleton instance of time perception manager
     *
     * @return TimePerceptionManager singleton instance
     *
     * FIXME: create a singleton instance.
     */
    public static TimePerceptionManager getInstance() {
        if (instance == null) {
            instance = new TimePerceptionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private TimePerceptionManager() {
        timePerceptionList = new ArrayList<>();
        turn = 0;
    }

    /**
     * Traversing through all instances in the list and execute them
     * By doing this way, it will avoid using `instanceof` all over the place.
     *
     * FIXME: write a relevant logic (i.e., increment turns choose day or night) and call this method once at every turn.
     */
    public void run() {
        String printDay = "It is a Day-time";
        String printNight = "it is a Night-time";

        ArrayList<TimePerception> timeList = new ArrayList<>(timePerceptionList);

        if (turn % 10 >= 5){
            System.out.printf("%s (turn %d)\n", printNight, turn++);
            timeList.forEach(TimePerception::nightEffect);
        }

        else {
            System.out.printf("%s (turn %d)\n", printDay, turn++);
            timeList.forEach(TimePerception::dayEffect);
        }


    }


    /**
     * Add the TimePerception instance to the list
     * FIXME: add objInstance to the list.
     * @param objInstance any instance that implements TimePerception
     */
    public void append(TimePerception objInstance) {
        timePerceptionList.add(objInstance);
    }


    /**
     * Remove a TimePerception instance from the list
     *
     * FIXME: [OPTIONAL] run cleanUp once every turn if you don't want to
     *        have too many instances in the list (e.g., memory leak)
     * @param objInstance object instance
     */
    public void cleanUp(TimePerception objInstance) {
        timePerceptionList.remove(objInstance);
    }




}
