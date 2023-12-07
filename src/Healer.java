public class Healer extends Character {
    protected int baseHealth = 14;

    public Healer(Pair damage, int armor) {
        super(damage, armor);
        super.setCriticalDamage(criticalDamage);
    }

    @Override
    public int getCriticalDamage() {
        return super.getCriticalDamage();
    }
}