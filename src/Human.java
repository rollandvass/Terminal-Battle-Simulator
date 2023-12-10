public class Human extends Character {

    protected int baseHealth = 100;
    protected int abilityValue;

    public Human(Pair damage, int armor, int abilityValue) {
        super(damage, armor);
        this.abilityValue = abilityValue;
    }

    @Override
    public int getAbilityValue() {
        return abilityValue;
    }

    @Override
    public String toString() {
        return getClass().getName() + "\t" + "[Health: " + getHealth()
                + " | Damage: " + getDamage().getFirst() + "-" + (getDamage().getSecond() * 2 + getAbilityValue())
                + " | Armor: " + getArmor() + "]";
    }

}