public class Human extends Character {

    protected int baseHealth = 12;

    public Human(Pair damage, int armor) {
        super(damage, armor);
        super.setCriticalDamage(criticalDamage - 1);
    }

    @Override
    public int getCriticalDamage() {
        return criticalDamage;
    }

}