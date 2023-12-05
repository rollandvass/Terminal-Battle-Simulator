public class Orc extends Character {

    // possible todo:
    // make orcs have a greater chance of critically striking

    protected int baseHealth = 18;

    public Orc(Pair damage, int armor) {
        super(damage, armor);
        super.setCriticalDamage(criticalDamage);
    }

    @Override
    public int getCriticalDamage() {
        return criticalDamage;
    }

}