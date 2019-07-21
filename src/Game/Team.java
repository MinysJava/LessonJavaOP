package Game;

public class Team extends Hero{

    public Team (Hero... hero){

    }

    public Team(int health, String name, int damage, int addHeal) {
        super(health, name, damage, addHeal);
    }

    @Override
    void hit(Game.Hero hero) {

    }

    @Override
    void healing(Game.Hero hero) {

    }
}
