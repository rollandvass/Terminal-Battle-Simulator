import java.util.concurrent.TimeUnit;

public class App {

    static CombatManager combatManager = new CombatManager();

    public static final String GREEN = "\033[0;32m"; // GREEN
    public static final String RESET = "\033[0m"; // RESET

    public static void main(String[] args) throws Exception {
        // winner depends on these factors:
        // - randomized damage (interval),
        // - randomized critical strike (15% chance),
        // - randomized parries (20% chance)

        Archer archer1 = new Archer(new Pair(4, 8), 0);
        Goblin goblin1 = new Goblin(new Pair(7, 9), 0);
        Knight knight1 = new Knight(new Pair(3, 4), 6);
        Ogre ogre1 = new Ogre(new Pair(5, 6), 4);

        combatManager.showBattleInfo(knight1, archer1);
        Character winner = fight(knight1, archer1);

        combatManager.showBattleInfo(winner, ogre1);
        winner = fight(winner, ogre1);

        combatManager.showBattleInfo(winner, goblin1);
        winner = fight(winner, goblin1);
        combatManager.showChampion(winner);

    }

    public static Character fight(Character attacker, Character defender)
            throws InterruptedException {
        Character winner = null;

        if (attacker.getHealth() > 0 && defender.getHealth() > 0) {
            combatManager.attack(attacker, defender);
            System.out.println(defender);
            System.out.println();
            TimeUnit.MILLISECONDS.sleep(250);

            winner = fight(defender, attacker);

        } else {
            if (attacker.getHealth() <= 0) {
                winner = defender;
                System.out
                        .print(GREEN + defender.getClass().getName() + " won with " + defender.getHealth() + " health");

                if (combatManager.hasArmor(defender)) {
                    System.out.print(" and " + defender.getArmor() + " armor");
                }

                System.out.println(" remaining!\n" + RESET);
                winner.resetStats(winner.getHealth(), winner.getArmor());
            }
            if (defender.getHealth() <= 0) {
                winner = attacker;
                System.out
                        .print(GREEN + attacker.getClass().getName() + " won with " + attacker.getHealth() + " health");

                if (combatManager.hasArmor(attacker)) {
                    System.out.print(" and " + attacker.getArmor() + " armor");
                }

                System.out.println(" remaining!\n" + RESET);
                winner.resetStats(winner.getHealth(), winner.getArmor());
            }
        }

        return winner;
    }
}