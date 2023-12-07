# Winner depends on these factors:

- randomized damage (interval),
- if abilities were cast
- randomized parries (20% chance)

-

> Showcase (of fighters):

Humans | Ability: Can double strike the enemy within the same turn (second hit = damage + doubleStrikeDamage)
Knight [Health: 14 | Damage: 3-4 | Armor: 6]
Archer [Health: 9 | Damage: 4-8 | Armor: 0]

Orcs | Ability: Can critically strike the enemy (destroys armor & damage = damage + criticalDamage)
Ogre [Health: 10 | Damage: 5-9 | Armor: 5]
Goblin [Health: 10 | Damage: 7-11 | Armor: 0]

Casters | Ability: Can cast a spell (ignores armor) that burns the enemy for 1-3 health each caster's turn
Warlock [Health: 12 | Damage: 4-5 | Armor: 0]
Shaman [Health: 11 | Damage: 5-6 | Armor: 0]

Healers | Ability: Can heal themselves for 4-6 health
Monk [Health: 19 | Damage: 2-4 | Armor: 1]
Priest [Health: 19 | Damage: 3-4 | Armor: 1]

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

> Abilities:

Humans | Can double strike the enemy within the same turn (second hit = damage + doubleStrikeDamage) (can be parried)
-Display text: [Attacker] double striked [Defender] and dealt x damage!

Orcs | Can critically strike enemy (destroys armor & damage = damage + criticalDamage) (can't be parried)
-Display text 1: [Attacker] critically striked [Defender] and dealt x damage!
-Display text 2: [Attacker] broke [Defender]'s armor and dealt x damage!

Casters | Can cast a spell that burns enemy for 1-3 health every caster's turn (spell burn ignores armor & can't be parried)
-Display text: [Attacker]'s spell burned [Defender] for X damage!

Healers | Can heal themselves for 4-6 health
-Display text: [Attacker] healed himself for X health!

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

# Warlock cast a spell on Knight!

Warlock attacked Knight and dealt 4 damage!

# Warlock's spell burned Knight for 2 health!

Knight [Health: 12 | Damage: 3-4 | Armor: 2]

# Warlock's spell burned Knight for 3 health!

Knight [Health: 9 | Damage: 3-4 | Armor: 2]

# Warlock's spell burned Knight for 1 health!

Knight [Health: 8 | Damage: 3-4 | Armor: 2]
.
.
.

> (Scenario 4: Healer heal)

Monk [Health: 13 | Damage: 2-4 | Armor: 0]

Monk attacked Knight and dealt 3 damage!

# Monk healed himself for 5 health!

Monk [Health: 18 | Damage: 2-4 | Armor: 0]
