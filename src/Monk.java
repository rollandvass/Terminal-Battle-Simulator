public class Monk extends Healer {

    public Monk(Pair damage, int armor, Pair abilityValueInterval) {
        super(damage, armor, abilityValueInterval);
        setHealth(baseHealth + 5);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth + 5);
        setArmor(initialArmor);
    }

    @Override
    public Pair getAbilityValueInterval() {
        return abilityValueInterval;
    }

}