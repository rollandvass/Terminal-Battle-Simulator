public class Knight extends Human {

    public Knight(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth + 2);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth + 2);
        setArmor(initialArmor);
    }

}