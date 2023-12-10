public class Knight extends Human {

    public Knight(Pair damage, int armor, int abilityValue) {
        super(damage, armor, abilityValue);
        setHealth(baseHealth + 5);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth + 5);
        setArmor(initialArmor);
    }

    @Override
    public int getAbilityValue() {
        return abilityValue;
    }

}