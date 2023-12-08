public class Warlock extends Caster {

    public Warlock(Pair damage, int armor, Pair abilityValueInterval) {
        super(damage, armor, abilityValueInterval);
        setHealth(baseHealth - 2);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth - 2);
        setArmor(initialArmor);
    }

    @Override
    public Pair getAbilityValueInterval() {
        return abilityValueInterval;
    }

}