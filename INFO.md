> Management technologies used: Trello, Git

-

> Testing.java:

- Used for testing the game.
- getGameStatistics(numberOfRuns) method takes a parameter of int type and calculates how many times a character won in [numberOfRuns] tournaments.

-

> App.java:

- The fight() method:

  - Is recursive and switches between the attacker & defender after one attack.
  - Returns a winner so the tournament can be implemented.

-

> CombatManager.java:

The attack() method:

```Java
if (attackSuccessful()) { // attacker successfully attacked defender

    if (abilityCasted()) { // attacker casted an ability (can't be parried)

        // instance checks
        if (attacker instanceof Human) {
            // double strike attack
            // handle defender armor
            // update defender health/armor
            // print attack
            // print defender
        }

        if (attacker instanceof Orc) {
            // critical strike attack (break armor)
            // update defender health/armor
            // print attack
            // print defender
        }

        if (attacker instanceof Caster) {
            // calculate burn amount
            // spell burn ignores armor, so update defender health only
            // print attack
            // print defender
        }

        if (attacker instanceof Healer) {
            // calculate heal amount
            // update attacker health (healer)
            // print heal
            // print attacker (healer)
        }

    } else { // no abilities were casted
        // classic attack
        // handle armor damage
        // print attack
    }

} else { // attack parried
    // print parry
}
```

# Winner depends on these factors:

- randomized damage (interval),
- randomized abilities (and who casted) - 15% chance,
- randomized parries (and who parried) - 20% chance

-

> Abilities:

Humans can double strike the enemy.
Orcs can critically strike the enemy.
Casters can cast a spell on the enemy.
Healers can heal themselves.

Double strike: Two hits & second hit has + damage
Critical strike: Breaks armor & + damage
Spell: Burns the enemy for x-y health on his turns & ignores armor
Heal: Heal for x-y health

-

> Tournament

winnerQF1 = fight(knight, warlock)
winnerQF2 = fight(ogre, monk)
winnerQF3 = fight(archer, shaman)
winnerQF4 = fight(goblin, priest)

winnerSF1 = fight(winnerQF1, winnerQF3)
winnerSF2 = fight(winnerQF2, winnerQF4)

finalWinner = fight(winnerSF1, winnerSF2)

-

> Stats:

Human: (baseHP = 12 | doubleStrikeDamage = 3)

- Knight [HP: baseHP + 2 | ATK: 3-4 | DEF: 6]
- Archer [HP: baseHP - 3 | ATK: 4-8 | DEF: 0]

Orc: (baseHP = 18 | criticalDamage = 3)

- Ogre [HP: baseHP / 2 + 1 | ATK: 5-6 (+criticalDamage) | DEF: 4]
- Goblin [HP: baseHP / 2 - 3 | ATK: 7-9 (+criticalDamage) | DEF: 0]

Caster: (baseHP = 10 | spellBurnDamage = 1-3)

- Warlock [HP: baseHP + 2 | ATK: 4-5 | DEF: 0]
- Shaman [HP: baseHP + 1 | ATK: 5-6 | DEF: 0]

Healer: (baseHP = 14 | heal = 4-6)

- Monk [HP: baseHP + 5 | ATK: 2-4 | DEF: 1]
- Priest [HP: baseHP + 3 | ATK: 3-4 | DEF: 1]

-

> Scenarios:

Knight [Health: 14 | Damage: 3-4 | Armor: 6]
Archer [Health: 9 | Damage: 4-8 | Armor: 0]

Ogre [Health: 9 | Damage: 5-6 | Armor: 4]
Goblin [Health: 6 | Damage: 7-9 | Armor: 0]

Warlock [Health: 12 | Damage: 4-5 | Armor: 0]
Shaman [Health: 11 | Damage: 5-6 | Armor: 0]

Monk [Health: 19 | Damage: 2-4 | Armor: 1]
Priest [Health: 17 | Damage: 3-4 | Armor: 1]

> Scenario 1: Human double strike

Knight attacks X with 3 damage
Knight double strikes X (abilityValue = 3)

Math:
3 - classic attack
6 - double strike attack
total = 9

Outcome:

# Knight double striked Ogre and dealt 9 damage!

Ogre [Health: 4 | Damage: 5-6 | Armor: 0]

> (Scenario 2.1: Orc critical strike)

Knight [Health: 14 | Damage: 3-4 | Armor: 6]

# Goblin broke Knight's armor and dealt 8 damage!

Knight [Health: 6 | Damage: 3-4 | Armor: 0]

> (Scenario 2.2: Orc critical strike)

Knight [Health: 12 | Damage 3-4 | Armor: 0]

# Goblin critically striked Knight and dealt 9 damage!

Knight [Health: 3 | Damage: 3-4 | Armor: 0]

> (Scenario 3: Caster spell)

Knight [Health: 14 | Damage: 3-4 | Armor: 6]

# Warlock's spell burned Knight for 2 health!

Knight [Health: 12 | Damage: 3-4 | Armor: 2]

# Warlock's spell burned Knight for 3 health!

Knight [Health: 9 | Damage: 3-4 | Armor: 2]

.
.
.

> (Scenario 4: Healer heal)

Monk [Health: 13 | Damage: 2-4 | Armor: 0]

Monk attacked Knight and dealt 3 damage!

# Monk healed himself for 5 health!

Monk [Health: 18 | Damage: 2-4 | Armor: 0]
