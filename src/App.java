import java.util.concurrent.TimeUnit;

public class App {

    static CombatManager combatManager = new CombatManager();

    public static final String GREEN = "\033[0;32m"; // GREEN
    public static final String RESET = "\033[0m"; // RESET

    public static void main(String[] args) throws Exception {
        // winner depends on these factors:
        // - randomized damage intervals,
        // - randomized critical strike (15% chance),
        // - randomized parries (35% chance)

        // Archer archer1 = new Archer(new Pair(4, 8), 0);
        // Goblin goblin1 = new Goblin(new Pair(7, 9), 0);

        // combatManager.showBattleInfo(archer1, goblin1);
        // fight(archer1, goblin1);

        Knight knight1 = new Knight(new Pair(3, 4), 6);
        Ogre ogre1 = new Ogre(new Pair(5, 6), 4);

        combatManager.showBattleInfo(knight1, ogre1);
        fight(knight1, ogre1);

    }

    public static void fight(Character attacker, Character defender)
            throws InterruptedException {
        if (attacker.getHealth() > 0 && defender.getHealth() > 0) {
            combatManager.attack(attacker, defender);
            System.out.println(defender);
            System.out.println();
            TimeUnit.MILLISECONDS.sleep(250);

            fight(defender, attacker);

        } else {
            if (attacker.getHealth() <= 0) {
                System.out.println(GREEN + defender.getClass().getName() + " won with " + defender.getHealth()
                        + " health and " + defender.getArmor() + " armor remaining!" + RESET);
            }
            if (defender.getHealth() <= 0) {
                System.out.println(GREEN + attacker.getClass().getName() + " won with " + attacker.getHealth()
                        + " health and " + attacker.getArmor() + " armor remaining!" + RESET);
            }
        }
    }
}

// Possible TODOs:
// tournament
// more classes (mount & blade factions (or troops))
// equipment ?