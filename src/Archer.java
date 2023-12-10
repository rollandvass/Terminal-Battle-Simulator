public class Archer extends Human {

    public Archer(Pair damage, int armor, int abilityValue) {
        super(damage, armor, abilityValue);
        setHealth(baseHealth - 20);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth - 20);
        setArmor(initialArmor);
    }

    @Override
    public int getAbilityValue() {
        return abilityValue;
    }

}