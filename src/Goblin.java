public class Goblin extends Orc {

    public Goblin(Pair damage, int armor, int abilityValue) {
        super(damage, armor, abilityValue);
        setHealth(baseHealth / 2 + 1);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth / 2 + 1);
        setArmor(initialArmor);
    }

    @Override
    public int getAbilityValue() {
        return abilityValue;
    }

}