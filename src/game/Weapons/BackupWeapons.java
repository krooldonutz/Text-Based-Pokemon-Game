package game.Weapons;


import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: ceci0001
 *
 * TODO: Use this class to store Pokemon's weapons (special attack) permanently.
 * If a Pokemon needs to use a weapon, put it into that Pokemon's inventory.
 * @see Actor#getWeapon() method.
 * @see AttackAction uses getWeapon() in the execute() method.
 */
public class BackupWeapons {

    /**
     * hashmap to store each special attack of respective pokemon
     */
    public Map<Integer, WeaponItem> specialAttack = new HashMap<>();

    /**
     * registers the special attack of the pokemon once caught
     * @param weaponId id of the special attack
     * @param weaponItem special attack of the pokemon
     */
    public void registerSpecialAttack(int weaponId, WeaponItem weaponItem) {
            specialAttack.put(weaponId, weaponItem);
    }

}

