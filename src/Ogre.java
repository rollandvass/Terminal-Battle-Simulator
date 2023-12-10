public class Ogre extends Orc {

    public Ogre(Pair damage, int armor, int abilityValue) {
        super(damage, armor, abilityValue);
        setHealth(baseHealth + 7);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth + 7);
        setArmor(initialArmor);
    }

    @Override
    public int getAbilityValue() {
        return abilityValue;
    }

}