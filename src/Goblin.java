public class Goblin extends Orc {

    public Goblin(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth / 2 - 3);
    }

    @Override
    public void resetStats(int health, int armor) {
        setHealth(baseHealth / 2 - 3);
    }

}