import java.util.Random;
// import java.util.concurrent.TimeUnit;

public class CombatManager {

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String RESET = "\033[0m";

    public static final String ITALIC = "\u001B[3m";
    public static final String BOLD = "\u001B[1m";

    // private static final int SLEEP_1000 = 0;

    int matchCounter = 0;

    public void attack(Character attacker, Character defender) throws InterruptedException {
        int damage = new Random().nextInt(attacker.getDamage().getFirst(), attacker.getDamage().getSecond() + 1);

        if (!attackSuccessful()) {
            System.out.println(CYAN + defender.getClass().getName() + " parried " + attacker.getClass().getName()
                    + "'s attack!" + RESET);
            return;
        }

        if (abilityCast()) {
            int abilityDamage = attacker.getAbilityValue();

            if (attacker instanceof Human) {
                int doubleStrikeDamage = (damage * 2) + abilityDamage;
                handleAbilityAttack(attacker, defender, doubleStrikeDamage, "double striked");
            } else if (attacker instanceof Orc) {
                int criticalStrikeDamage = damage + abilityDamage;
                handleAbilityAttack(attacker, defender, criticalStrikeDamage, "critically striked");
            } else if (attacker instanceof Caster) {
                int burnDamage = new Random().nextInt(attacker.getAbilityValueInterval().getFirst(),
                        attacker.getAbilityValueInterval().getSecond() + 1);
                defender.setHealth(defender.getHealth() - burnDamage);
                System.out.println(YELLOW + attacker.getClass().getName() + "'s spell burned "
                        + defender.getClass().getName() + " for " + RED + burnDamage + YELLOW + " health!" + RESET);
                if (defender.getHealth() > 0) {
                    System.out.println(defender);
                }
            } else if (attacker instanceof Healer) {
                int healAmount = new Random().nextInt(attacker.getAbilityValueInterval().getFirst(),
                        attacker.getAbilityValueInterval().getSecond() + 1);
                attacker.setHealth(attacker.getHealth() + healAmount);
                System.out.println(YELLOW + attacker.getClass().getName() + " healed himself for " + GREEN + healAmount
                        + YELLOW + " health!" + RESET);
                System.out.println(attacker);
            }
        } else {
            handleClassicAttack(attacker, defender, damage);
        }
    }

    private void handleAbilityAttack(Character attacker, Character defender, int totalDamage, String attackType) {
        if (hasArmor(defender)) {
            int remainderDamage = totalDamage - defender.getArmor();
            defender.setArmor(Math.max(0, defender.getArmor() - totalDamage));

            if (remainderDamage > 0) {
                defender.setHealth(defender.getHealth() - remainderDamage);
            }
        } else {
            defender.setHealth(defender.getHealth() - totalDamage);
        }

        System.out
                .println(YELLOW + attacker.getClass().getName() + " " + attackType + " " + defender.getClass().getName()
                        + " and dealt " + RED + totalDamage + YELLOW + " damage!" + RESET);

        if (defender.getHealth() > 0) {
            System.out.println(defender);
        }
    }

    private void handleClassicAttack(Character attacker, Character defender, int damage) {
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

    // 80% success rate of attack || 20% chance of parry
    public boolean attackSuccessful() {
        int random = new Random().nextInt(1, 101);
        return random <= 80;
    }

    // 15% chance to cast ability
    public boolean abilityCast() {
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

    public void showBattleInfo(Character character1, Character character2) throws InterruptedException {
        String match = "";
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
        // TimeUnit.MILLISECONDS.sleep(SLEEP_1000); // 1000
        System.err.print("\t* ");
        showCharacterInfo(character1);
        // TimeUnit.MILLISECONDS.sleep(SLEEP_1000); // 1000
        System.out.print("  VS  ");
        // TimeUnit.MILLISECONDS.sleep(SLEEP_1000); // 1000
        showCharacterInfo(character2);
        System.err.print(" *");
        System.out.println("\n");
        // TimeUnit.MILLISECONDS.sleep(SLEEP_1000); // 1000
        System.out.println(character1);
        System.out.println(character2 + "\n");
        // TimeUnit.MILLISECONDS.sleep(SLEEP_1000); // 1000
        System.out.println("Fight!\n");
        // TimeUnit.MILLISECONDS.sleep(SLEEP_1000); // 1000
    }

    public void showClassesAndAbilities() {
        System.out.println("Classes and abilities:");
        System.out.println("- Humans can double strike the enemy.");
        System.out.println("- Orcs can critically strike the enemy.");
        System.out.println("- Casters can cast a spell on the enemy.");
        System.out.println("- Healers can heal themselves.\n");

        System.out.println("> Double strike: Two hits & second hit has more damage");
        System.out.println("> Critical strike: Breaks armor & more damage");
        System.out.println("> Spell: Burns the enemy for some health & ignores armor");
        System.out.println("> Heal: Heal for some health\n");
    }

    public void showCharacterInfo(Character character) {
        System.out.print(ITALIC + BOLD + character.getClass().getName() + RESET);
    }

    public void showChampion(Character character) {
        System.out
                .print(App.GREEN + character.getClass().getName() + " is the champion!\n" + RESET);
    }
}