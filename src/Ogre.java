public class Ogre extends Orc {

    public Ogre(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth / 2);
    }

    @Override
    public void resetStats(int health, int armor) {
        setHealth(baseHealth / 2);
        // reset armor
    }

}