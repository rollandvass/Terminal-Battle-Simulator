import java.util.concurrent.TimeUnit;

public class App {

    static CombatManager combatManager = new CombatManager();

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";

    private static final int SLEEP_500 = 0;

    static Knight knight = new Knight(new Pair(2, 3), 4, 1);
    static Archer archer = new Archer(new Pair(3, 6), 0, 1);
    static Ogre ogre = new Ogre(new Pair(3, 5), 2, 2);
    static Goblin goblin = new Goblin(new Pair(4, 7), 0, 2);
    static Warlock warlock = new Warlock(new Pair(4, 5), 0, new Pair(1, 3));
    static Shaman shaman = new Shaman(new Pair(5, 6), 0, new Pair(1, 3));
    static Monk monk = new Monk(new Pair(2, 4), 1, new Pair(2, 5));
    static Priest priest = new Priest(new Pair(2, 5), 1, new Pair(1, 4));

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
            TimeUnit.MILLISECONDS.sleep(SLEEP_500); // 250

            winner = fight(defender, attacker);

        } else {
            if (attacker.getHealth() <= 0) {
                winner = defender;
            } else {
                winner = attacker;
            }

            combatManager.showKilled(attacker, defender);
            TimeUnit.MILLISECONDS.sleep(SLEEP_500);
            System.out
                    .print(GREEN + winner.getClass().getName() + " won with " +
                            winner.getHealth() + " health");

            if (combatManager.hasArmor(winner)) {
                System.out.print(" and " + winner.getArmor() + " armor");
            }

            System.out.println(" remaining!\n" + RESET);
            attacker.resetStats();
            defender.resetStats();

        }

        return winner;
    }
}