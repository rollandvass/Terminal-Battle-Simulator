public class Healer extends Character {

    protected int baseHealth = 14;
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
        // Ensure that the healer's health doesn't go over the maximum value
        this.health = Math.min(health, baseHealth + 5);
    }
}