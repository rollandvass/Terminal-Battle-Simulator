public class Caster extends Character {

    protected int baseHealth = 14;
    protected Pair abilityValueInterval;

    public Caster(Pair damage, int armor, Pair abilityValueInterval) {
        super(damage, armor);
        this.abilityValueInterval = abilityValueInterval;
    }

    @Override
    public Pair getAbilityValueInterval() {
        return abilityValueInterval;
    }

}