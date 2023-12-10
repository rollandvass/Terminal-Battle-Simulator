public class Caster extends Character {

    protected int baseHealth = 100;
    protected Pair abilityValueInterval;

    public Caster(Pair damage, int armor, Pair abilityValueInterval) {
        super(damage, armor);
        this.abilityValueInterval = abilityValueInterval;
    }

    @Override
    public Pair getAbilityValueInterval() {
        return abilityValueInterval;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\t" + "[Health: " + getHealth()
                + " | Damage: " + getDamage().getFirst() + "-" + (getDamage().getSecond() + getAbilityValue())
                + " | Armor: " + getArmor() + "]";
    }

}