public class Knight extends Human {

    public Knight(Pair damage, int armor, int abilityValue) {
        super(damage, armor, abilityValue);
        setHealth(baseHealth + 1);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth + 1);
        setArmor(initialArmor);
    }

}