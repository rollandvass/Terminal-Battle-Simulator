public class Archer extends Human {

    public Archer(Pair damage, int armor, int abilityValue) {
        super(damage, armor, abilityValue);
        setHealth(baseHealth - 2);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth - 2);
        setArmor(initialArmor);
    }

}