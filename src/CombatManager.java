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

    int turnCounter = 0;

    public void attack(Character attacker, Character defender) throws InterruptedException {
        turnCounter++;
        int damage = new Random().nextInt(attacker.getDamage().getFirst(), attacker.getDamage().getSecond() + 1);

        if (attackSuccessful()) { // good attack

            // check for ability cast
            if (abilityCasted()) { // ability cast

                // instance checks
                if (attacker instanceof Human) { // human - double strike
                    int doubleStrikeDamage = (damage * 2) + attacker.getAbilityValue();
                    // handle armor
                    if (hasArmor(defender)) {
                        int remainderDamage = doubleStrikeDamage - defender.getArmor();
                        defender.setArmor(Math.max(0, defender.getArmor() - doubleStrikeDamage));

                        if (remainderDamage > 0) {
                            defender.setHealth(defender.getHealth() - remainderDamage);
                        }
                    } else {
                        defender.setHealth(defender.getHealth() - doubleStrikeDamage);
                    }

                    System.out.println(YELLOW + attacker.getClass().getName() + " double striked "
                            + defender.getClass().getName() + " and dealt " + RED + doubleStrikeDamage + YELLOW
                            + " damage!" + RESET);

                    if (defender.getHealth() > 0) {
                        System.out.println(defender);
                    }
                }

                if (attacker instanceof Orc) { // orc - critical strike (breaks armor if present)
                    int criticalStrikeDamage = damage + attacker.getAbilityValue();
                    // break armor
                    if (hasArmor(defender)) {
                        defender.setArmor(0);
                        defender.setHealth(defender.getHealth() - criticalStrikeDamage);
                        System.out.println(
                                YELLOW + attacker.getClass().getName() + " broke " + defender.getClass().getName()
                                        + "'s armor and dealt " + RED + criticalStrikeDamage + YELLOW + " damage!"
                                        + RESET);
                    } else {
                        defender.setHealth(defender.getHealth() - criticalStrikeDamage);
                        System.out.println(
                                YELLOW + attacker.getClass().getName() + " critically striked "
                                        + defender.getClass().getName()
                                        + " and dealt " + RED + criticalStrikeDamage + YELLOW + " damage!" + RESET);
                    }

                    if (defender.getHealth() > 0) {
                        System.out.println(defender);
                    }
                }

                if (attacker instanceof Caster) { // caster - burn enemy every second turn
                    int burnDamage = new Random().nextInt(attacker.getAbilityValueInterval().getFirst(),
                            attacker.getAbilityValueInterval().getSecond() + 1);
                    // ignores armor
                    defender.setHealth(defender.getHealth() - burnDamage);
                    System.out.println(
                            YELLOW + attacker.getClass().getName() + "'s spell burned "
                                    + defender.getClass().getName()
                                    + " for " + RED + burnDamage + YELLOW + " health!" + RESET);

                    if (defender.getHealth() > 0) {
                        System.out.println(defender);
                    }
                    // not finished
                    // once the spell is cast the enemy will be burned on his turns
                }

                if (attacker instanceof Healer) { // healer - heals for some health
                    int healAmount = new Random().nextInt(attacker.getAbilityValueInterval().getFirst(),
                            attacker.getAbilityValueInterval().getSecond() + 1);
                    attacker.setHealth(attacker.getHealth() + healAmount);
                    System.out.println(
                            YELLOW + attacker.getClass().getName() + " healed himself for " + GREEN + healAmount
                                    + YELLOW
                                    + " health!" + RESET);
                    System.out.println(attacker);
                }

            } else { // classic attack
                // handle armor
                if (hasArmor(defender)) {
                    int remainderDamage = damage - defender.getArmor();
                    defender.setArmor(Math.max(0, defender.getArmor() - damage));

                    if (remainderDamage > 0) {
                        defender.setHealth(defender.getHealth() - remainderDamage);
                    }
                } else {
                    defender.setHealth(defender.getHealth() - damage);
                }

                System.out.println(PURPLE + attacker.getClass().getName() + " attacked " + defender.getClass().getName()
                        + " and dealt " + RED + damage + PURPLE + " damage!" + RESET);

                if (defender.getHealth() > 0) {
                    System.out.println(defender);
                }
            }
        } else { // attack parried
            System.out.println(CYAN + defender.getClass().getName() + " parried " + attacker.getClass().getName()
                    + "'s attack!" + RESET);
        }
    }

    // 80% success rate of attack || 20% chance of parry
    public boolean attackSuccessful() {
        int random = new Random().nextInt(1, 101);
        return random <= 80;
    }

    // 15% chance to cast ability
    public boolean abilityCasted() {
        int random = new Random().nextInt(1, 101);
        return random <= 15;
    }

    public boolean hasArmor(Character defender) {
        return defender.getArmor() > 0;
    }

    public boolean isTurnEven() {
        return turnCounter % 2 == 0;
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
            System.out.println(character1);
            System.out.println(character2 + "\n");
            TimeUnit.MILLISECONDS.sleep(SLEEP_500); // 1000
            System.out.println("Fight!\n");
            TimeUnit.MILLISECONDS.sleep(SLEEP_500); // 1000
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showClassesAndAbilities() {
        System.out.println("Classes and abilities:\n");
        System.out.println("Humans can double strike the enemy.");
        System.out.println("Orcs can critically strike the enemy.");
        System.out.println("Casters can cast a spell on the enemy.");
        System.out.println("Healers can heal themselves.\n");

        System.out.println("Double strike: Two hits & second hit has +1 damage");
        System.out.println("Critical strike: Breaks armor & +2 damage");
        System.out.println("Spell: Burns the enemy for 1-3 health on his turns & ignores armor");
        System.out.println("Heal: Heal for 3-5 health\n");
    }

    public void showCharacterInfo(Character character) {
        System.out.print(ITALIC + BOLD + character.getClass().getName() + RESET);
    }

    public void showChampion(Character character) {
        System.out
                .print(App.GREEN + character.getClass().getName() + " is the champion!\n" + RESET);
    }
}