public class Shaman extends Caster {

    public Shaman(Pair damage, int armor, Pair abilityValueInterval) {
        super(damage, armor, abilityValueInterval);
        setHealth(baseHealth - 3);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth - 3);
        setArmor(initialArmor);
    }

    @Override
    public Pair getAbilityValueInterval() {
        return abilityValueInterval;
    }

}