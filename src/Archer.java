public class Archer extends Human {

    public Archer(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth - 3);
    }

    @Override
    public void resetStats(int health, int armor) {
        setHealth(baseHealth - 3);
    }

}