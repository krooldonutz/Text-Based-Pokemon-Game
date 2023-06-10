package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Scanner;

import game.PokeItems.PokeBall;
import game.PokeItems.PokeEgg;
import game.PokeItems.PokeType;
import game.PokeItems.Pokefruit;
import game.actors.Player;
import game.utilities.Element;

public class TradeAction extends Action {
    public String execute(Actor actor, GameMap map) {
        int option = tradeMenu();
        switch (option) {
            case 1:
                buyPokeFruit((Player) actor, Element.FIRE);
                break;
            case 2:
                buyPokeFruit((Player) actor, Element.WATER);
                break;
            case 3:
                buyPokeFruit((Player) actor, Element.GRASS);
                break;
            case 4:
                buyPokeEgg((Player) actor, Element.FIRE);
                break;
            case 5:
                buyPokeEgg((Player) actor, Element.WATER);
                break;
            case 6:
                buyPokeEgg((Player) actor, Element.GRASS);
                break;
        }
        return "";
    }
    public String menuDescription(Actor actor) {
        return actor + " visits the PokeMart";
    }

    public static int tradeMenu() {
        int option;

        Scanner userInput =  new Scanner(System.in);

        System.out.println("Welcome to PokeMart! What can I get you today?");
        System.out.println("1) Fire PokeFruit (Cost: 1 Candy)");
        System.out.println("2) Water PokeFruit (Cost: 1 Candy)");
        System.out.println("3) Grass PokeFruit (Cost: 1 Candy)");
        System.out.println("4) Charmander Egg (Cost: 5 Candy)");
        System.out.println("5) Squirtle Egg (Cost: 5 Candy)");
        System.out.println("6) Bulbasaur Egg (Cost: 5 Candy)");

        System.out.println("Select one: ");
        option = Integer.parseInt(userInput.nextLine());

        return option;
    }

    public static boolean removeCandies(Player player) {
        for (Item item: player.getInventory()) {
            if (item.hasCapability(PokeType.CANDY)) {
                player.removeItemFromInventory(item);
                return true;
            }
        }
        System.out.println("Insufficient funds of candy");
        return false;
    }

    public static void buyPokeFruit(Player player, Element element){

        if(removeCandies(player)) {
            Pokefruit tradePokeFruit = new Pokefruit(element);
            player.addItemToInventory(tradePokeFruit);
            System.out.println(String.format("%s is bought!", tradePokeFruit.getPokeFruitName(element)));
        }
        else {
            System.out.println("Transaction Failed.");
        }
    }

    public static void buyPokeEgg(Player player, Element element){
        int candyAmount = 0;
        for (Item item : player.getInventory()) {
            if (item.hasCapability(PokeType.CANDY)) {
                candyAmount++;
            }
        }
        if (candyAmount >= 5) {
            for (int i = 0; i < 5; i++) {
                removeCandies(player);
            }
            PokeEgg tradePokeEgg = new PokeEgg(element);
            player.addItemToInventory(tradePokeEgg);
            System.out.println(String.format("%s is bought!!", tradePokeEgg.getPokeEggName(element)));
        }
        else {
            System.out.println("Insufficient funds of candy");
            System.out.println("Transaction Failed.");
        }
    }
}
