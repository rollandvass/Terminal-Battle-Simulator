public class Character {

    private int health, armor;
    protected int initialArmor;
    private Pair damage;
    protected int criticalDamage = 3;

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

    public void setCriticalDamage(int criticalDamage) {
        this.criticalDamage = criticalDamage;
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

    public int getCriticalDamage() {
        return criticalDamage;
    }

    public void resetStats() {
        setHealth(health);
        setArmor(initialArmor);
    }

    @Override
    public String toString() {
        return getClass().getName() + "\t" + "[Health: " + getHealth()
                + " | Damage: " + getDamage().getFirst() + "-" + (getDamage().getSecond() + getCriticalDamage())
                + " | Armor: " + getArmor() + "]";
    }

}