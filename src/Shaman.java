public class Shaman extends Caster {

    public Shaman(Pair damage, int armor) {
        super(damage, armor);
        setHealth(baseHealth - 3);
    }

    @Override
    public void resetStats() {
        setHealth(baseHealth - 3);
        setArmor(initialArmor);
    }

}