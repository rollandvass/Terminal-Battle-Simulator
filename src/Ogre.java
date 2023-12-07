public class Ogre extends Orc {

    public Ogre(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth / 2 + 1);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth / 2 + 1);
        setArmor(initialArmor);
    }

}