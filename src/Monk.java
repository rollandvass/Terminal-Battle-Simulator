public class Monk extends Healer {

    public Monk(Pair damage, int armor, Pair abilityValueInterval) {
        super(damage, armor, abilityValueInterval);
        setHealth(baseHealth + 35);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth + 35);
        setArmor(initialArmor);
    }

    @Override
    public Pair getAbilityValueInterval() {
        return abilityValueInterval;
    }

}