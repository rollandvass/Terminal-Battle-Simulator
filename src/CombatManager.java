import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CombatManager {

    public static final String RED = "\033[0;31m";
    public static final String YELLOW = "\033[0;33m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String RESET = "\033[0m";

    private static final int SLEEP_DURATION = 500;

    public void attack(Character attacker, Character defender) throws InterruptedException {
        int damage = new Random().nextInt(attacker.getDamage().getFirst(), attacker.getDamage().getSecond() + 1);

        if (attackSuccessful()) {
            if (criticallyStriked()) {
                TimeUnit.MILLISECONDS.sleep(SLEEP_DURATION);
                String message = YELLOW + attacker.getClass().getName() +
                        (hasArmor(defender) ? " broke " + defender.getClass().getName() + "'s armor and"
                                : " critically striked " + defender.getClass().getName() + " and")
                        +
                        " dealt " + RED + (attacker.getCriticalDamage() + damage) + YELLOW + " damage!" + RESET;
                System.out.println(message);
                defender.setHealth(Math.max(0, defender.getHealth() - attacker.getCriticalDamage()));
                defender.setArmor(0);
                TimeUnit.MILLISECONDS.sleep(SLEEP_DURATION);
            } else {
                TimeUnit.MILLISECONDS.sleep(SLEEP_DURATION);
                String message = PURPLE + attacker.getClass().getName() +
                        " attacked " + defender.getClass().getName() +
                        " and dealt " + RESET + RED + damage + RESET + PURPLE + " damage!" + RESET;
                System.out.println(message);
                TimeUnit.MILLISECONDS.sleep(SLEEP_DURATION);
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
        } else {
            TimeUnit.MILLISECONDS.sleep(SLEEP_DURATION);
            System.out.println(CYAN + defender.getClass().getName() +
                    " parried " + attacker.getClass().getName() + "'s attack!" + RESET);
            TimeUnit.MILLISECONDS.sleep(SLEEP_DURATION);
        }
    }

    public boolean hasArmor(Character defender) {
        return defender.getArmor() > 0;
    }

    // 75% success rate of attack OR 35% chance of parry
    public boolean attackSuccessful() {
        int random = new Random().nextInt(1, 101);
        return random <= 75;
    }

    // 15% chance to critical strike
    public boolean criticallyStriked() {
        int random = new Random().nextInt(1, 101);
        return random <= 15;
    }

    public void showBattleInfo(Character character1, Character character2) {
        try {
            showCharacterInfo(character1);
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
            System.out.println("\tVS");
            System.out.println();
            TimeUnit.SECONDS.sleep(1);
            showCharacterInfo(character2);
            System.out.println();
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void showCharacterInfo(Character character) {
        System.out.println(character.getClass().getName() + "\t" + "[Health: " + character.getHealth()
                + " | Damage: " + character.getDamage().getFirst() + "-"
                + (character.getDamage().getSecond() + character.getCriticalDamage())
                + " | Armor: " + character.getArmor() + "]");
    }
}