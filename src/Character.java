public class Character {

    private int health, armor;
    private Pair damage;
    protected int abilityDamage = 3;

    public Character(Pair damage, int armor) {
        setDamage(damage);
        setArmor(armor);
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

    public void setAbilityDamage(int abilityDamage) {
        this.abilityDamage = abilityDamage;
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

    public int getAbilityDamage() {
        return abilityDamage;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\t[Health: " + getHealth() + " | Armor: " + getArmor() + "]";
    }

}