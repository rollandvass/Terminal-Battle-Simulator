import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CombatManager {

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String RESET = "\033[0m";

    public static final String ITALIC = "\u001B[3m";
    public static final String BOLD = "\u001B[1m";

    private static final int SLEEP_500 = 0;

    int matchCounter = 0;

    public void attack(Character attacker, Character defender) throws InterruptedException {
        int damage = new Random().nextInt(attacker.getDamage().getFirst(), attacker.getDamage().getSecond() + 1);

        if (attackSuccessful()) {
            // critical strike can't be parried
            if (criticallyStriked()) {
                TimeUnit.MILLISECONDS.sleep(SLEEP_500);
                String message = YELLOW + attacker.getClass().getName() +
                        (hasArmor(defender) ? " broke " + defender.getClass().getName() + "'s armor and"
                                : " critically striked " + defender.getClass().getName() + " and")
                        +
                        " dealt " + RED + (attacker.getCriticalDamage() + damage) + YELLOW + " damage!" + RESET;
                System.out.println(message);
                defender.setHealth(Math.max(0, defender.getHealth() - attacker.getCriticalDamage()));
                defender.setArmor(0);
                TimeUnit.MILLISECONDS.sleep(SLEEP_500);
            } else {
                TimeUnit.MILLISECONDS.sleep(SLEEP_500);
                String message = PURPLE + attacker.getClass().getName() +
                        " attacked " + defender.getClass().getName() +
                        " and dealt " + RESET + RED + damage + RESET + PURPLE + " damage!" + RESET;
                System.out.println(message);
                TimeUnit.MILLISECONDS.sleep(SLEEP_500);
            }

            if (hasArmor(defender)) {
                int remainderDamage = Math.max(0, damage - defender.getArmor());
                defender.setArmor(Math.max(0, defender.getArmor() - damage));

                if (remainderDamage > 0) {
                    defender.setHealth(Math.max(0, defender.getHealth() - remainderDamage));
                }
            } else {
                defender.setHealth(Math.max(0, defender.getHealth() - damage));
            }

            if (defender.getHealth() > 0) {
                System.out.println(defender);
            }

        } else {
            TimeUnit.MILLISECONDS.sleep(SLEEP_500);
            System.out.println(CYAN + defender.getClass().getName() +
                    " parried " + attacker.getClass().getName() + "'s attack!" + RESET);
            TimeUnit.MILLISECONDS.sleep(SLEEP_500);
        }
    }

    // 80% success rate of attack || 20% chance of parry
    public boolean attackSuccessful() {
        int random = new Random().nextInt(1, 101);
        return random <= 80;
    }

    // 15% chance to critical strike
    public boolean criticallyStriked() {
        int random = new Random().nextInt(1, 101);
        return random <= 15;
    }

    public boolean hasArmor(Character defender) {
        return defender.getArmor() > 0;
    }

    public void showKilled(Character attacker, Character defender) throws InterruptedException {
        if (defender.getHealth() <= 0) {
            System.out.println(RED + defender.getClass().getName() + " died!\n"
                    + RESET);
        } else {
            System.out.println(RED + attacker.getClass().getName() + " died!\n"
                    + RESET);
        }
    }

    public void showBattleInfo(Character character1, Character character2) {
        String match = "";
        try {
            matchCounter++;
            switch (matchCounter) {
                case 1, 2, 3, 4:
                    match = "Quarter Finals";
                    break;
                case 5, 6:
                    match = "Semi Finals";
                    break;
                case 7:
                    match = "Finals";
                    break;
                default:
                    match = "Match";
                    break;
            }
            System.out.println("--------------- " + match + " ---------------");
            System.out.println();
            TimeUnit.MILLISECONDS.sleep(SLEEP_500); // 1000
            System.err.print("\t* ");
            showCharacterInfo(character1);
            TimeUnit.MILLISECONDS.sleep(SLEEP_500); // 1000
            System.out.print("  VS  ");
            TimeUnit.MILLISECONDS.sleep(SLEEP_500); // 1000
            showCharacterInfo(character2);
            System.err.print(" *");
            System.out.println("\n");
            TimeUnit.MILLISECONDS.sleep(SLEEP_500); // 1000
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showFighters(ArrayList<Character> characters) {
        for (Character fighter : characters) {
            System.out.println(fighter);
        }
        System.out.println();
    }

    public void showCharacterInfo(Character character) {
        System.out.print(ITALIC + BOLD + character.getClass().getName() + RESET);
    }

    public void showChampion(Character character) {
        System.out
                .print(App.GREEN + character.getClass().getName() + " is the champion!\n" + RESET);
    }
}