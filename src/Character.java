public class Character {

    protected int health, armor;
    protected int initialArmor;
    private Pair damage;

    protected int abilityValue;
    protected Pair abilityValueInterval;

    public Character(Pair damage, int armor) {
        setDamage(damage);
        setArmor(armor);
        initialArmor = armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(Pair damage) {
        this.damage = damage;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setAbilityValue(int abilityValue) {
        this.abilityValue = abilityValue;
    }

    public void setAbilityValueInterval(Pair abilityValueInterval) {
        this.abilityValueInterval = abilityValueInterval;
    }

    public int getHealth() {
        return health;
    }

    public Pair getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public int getAbilityValue() {
        return abilityValue;
    }

    public Pair getAbilityValueInterval() {
        return abilityValueInterval;
    }

    public void resetStats() {
        setHealth(health);
        setArmor(initialArmor);
    }

    @Override
    public String toString() {
        return getClass().getName() + "\t" + "[Health: " + getHealth()
                + " | Damage: " + getDamage().getFirst() + "-" + getDamage().getSecond()
                + " | Armor: " + getArmor() + "]";
    }

}