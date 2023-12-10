public class Healer extends Character {

    protected int baseHealth = 100;
    protected Pair abilityValueInterval;

    public Healer(Pair damage, int armor, Pair abilityValueInterval) {
        super(damage, armor);
        this.abilityValueInterval = abilityValueInterval;
    }

    @Override
    public Pair getAbilityValueInterval() {
        return abilityValueInterval;
    }

    @Override
    public void setHealth(int health) {
        this.health = Math.min(health, baseHealth + 35);
    }

    @Override
    public String toString() {
        return getClass().getName() + "\t" + "[Health: " + getHealth()
                + " | Damage: " + getDamage().getFirst() + "-" + (getDamage().getSecond() + getAbilityValue())
                + " | Armor: " + getArmor() + "]";
    }
}