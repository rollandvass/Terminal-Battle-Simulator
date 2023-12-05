public class Archer extends Human {

    public Archer(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth - 2);
    }

}