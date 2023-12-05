public class Ogre extends Orc {

    public Ogre(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth / 2);
    }

}