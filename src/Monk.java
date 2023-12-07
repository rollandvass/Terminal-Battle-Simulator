public class Monk extends Healer {

    public Monk(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth + 5);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth + 5);
        setArmor(initialArmor);
    }

}