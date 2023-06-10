package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Spawn.Crater;
import game.Spawn.Tree;
import game.Spawn.Waterfall;
import game.actors.Goh;
import game.actors.NurseJoy;
import game.actors.Player;
import game.actors.Pokemon.Bulbasaur;
import game.actors.Pokemon.Charmander;
import game.actors.Pokemon.Squirtle;
import game.grounds.*;

import java.util.Arrays;
import java.util.List;

/**
 * The main class to start the game.
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Tree(),
                new Lava(), new Puddle(), new Crater(), new Waterfall(), new Hay(), new Door(), new Incubator());

        List<String> map = Arrays.asList(
                ".............................................^^^^^^O^^^^^^^",
                "...........,..........,,,,......O.............,.,..^^^^^^^^",
                ".........,,T,.........,T,,...........................^^^^^^",
                "...........,,.........,,,,..............................^^^",
                ".........................................................^^",
                ".....................,......###...........................^",
                "....................,T,.....#.#............................",
                "...........~.........,.......................O.............",
                "...~~~~~W~~................................................",
                "....~~~~~..................................................",
                "~~~.~~~.....................................O..............",
                "~W~~~~..........O..........................................",
                "~~~~~~~~~..................................................");

        List<String> pC = Arrays.asList(
                "##################",
                "#________________#",
                "#______....._____#",
                "#________________#",
                "#__X__________X__#",
                "########_._#######");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);
        String gameMapTitle = "Main Land";

        GameMap pokemonCentre = new GameMap(groundFactory, pC);
        world.addGameMap(pokemonCentre);
        String pokemonCentreTitle = "Pokemon Centre";

        //Add player - Ash
        Player ash = Player.getInstance();
        world.addPlayer(ash, gameMap.at(28, 7));

        Goh goh = Goh.getInstance();
        gameMap.at(12, 12).addActor(goh);

        //Add Nurse Joy
        NurseJoy joy = new NurseJoy();
        pokemonCentre.at(9,2).addActor(joy);

        Charmander charmander = new Charmander();
        gameMap.at(30, 12).addActor(charmander);
        gameMap.at(13,12).addActor(new Squirtle());
        gameMap.at(25,4).addActor(new Bulbasaur());

        gameMap.at(29, 6).setGround(new Door(pokemonCentre.at(9, 5), gameMapTitle, pokemonCentreTitle));


        world.run();

    }
}
