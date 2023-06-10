package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;

public class Wall extends enableSummon {

	public Wall() {
		super('#');
	}



    @Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
