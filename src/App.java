// import java.util.concurrent.TimeUnit;

public class App {

    static CombatManager combatManager = new CombatManager();

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";

    // private static final int SLEEP_500 = 0;

    static Knight knight = new Knight(new Pair(20, 30), 29, 5);
    static Archer archer = new Archer(new Pair(29, 61), 0, 10);
    static Ogre ogre = new Ogre(new Pair(23, 44), 21, 10);
    static Goblin goblin = new Goblin(new Pair(33, 70), 0, 15);
    static Warlock warlock = new Warlock(new Pair(35, 55), 0, new Pair(11, 37));
    static Shaman shaman = new Shaman(new Pair(43, 58), 0, new Pair(10, 32));
    static Monk monk = new Monk(new Pair(22, 45), 12, new Pair(23, 52));
    static Priest priest = new Priest(new Pair(20, 55), 11, new Pair(15, 54));

    public static void main(String[] args) throws Exception {

        combatManager.showClassesAndAbilities();

        combatManager.showBattleInfo(knight, warlock);
        Character winnerQF1 = fight(knight, warlock);

        combatManager.showBattleInfo(ogre, monk);
        Character winnerQF2 = fight(ogre, monk);

        combatManager.showBattleInfo(archer, shaman);
        Character winnerQF3 = fight(archer, shaman);

        combatManager.showBattleInfo(goblin, priest);
        Character winnerQF4 = fight(goblin, priest);

        combatManager.showBattleInfo(winnerQF1, winnerQF3);
        Character winnerSF1 = fight(winnerQF1, winnerQF3);

        combatManager.showBattleInfo(winnerQF2, winnerQF4);
        Character winnerSF2 = fight(winnerQF2, winnerQF4);

        combatManager.showBattleInfo(winnerSF1, winnerSF2);
        Character finalWinner = fight(winnerSF1, winnerSF2);

        combatManager.showChampion(finalWinner);

    }

    public static Character fight(Character attacker, Character defender)
            throws InterruptedException {
        Character winner = null;

        if (attacker.getHealth() > 0 && defender.getHealth() > 0) {
            combatManager.attack(attacker, defender);
            System.out.println();
            // TimeUnit.MILLISECONDS.sleep(SLEEP_500); // 250

            winner = fight(defender, attacker);

        } else {
            if (attacker.getHealth() <= 0) {
                winner = defender;
            } else {
                winner = attacker;
            }

            combatManager.showKilled(attacker, defender);
            // TimeUnit.MILLISECONDS.sleep(SLEEP_500);
            System.out
                    .print(GREEN + winner.getClass().getName() + " won with " +
                            winner.getHealth() + " health");

            if (combatManager.hasArmor(winner)) {
                System.out.print(" and " + winner.getArmor() + " armor");
            }

            System.out.println(" remaining!\n" + RESET);
            attacker.resetStats();
            defender.resetStats();
            combatManager.resetTurns();
        }

        return winner;
    }
}