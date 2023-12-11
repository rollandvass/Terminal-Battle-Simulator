# Text/Console Based Battle Simulator

Welcome to Battle Simulator, a text and console-based game where characters from different races clash in a tournament. Dive into the world of Humans, Orcs, Casters, and Healers, each with unique abilities, and experience thrilling battles with randomized outcomes.

## How to Play

1. **Clone the repository to your local machine:**
    ```bash
    git clone https://github.com/iRxllz/Battle-Simulator.git
    cd Battle-Simulator
    ```

2. **Compile and run the game:**
    ```bash
    javac App.java
    java App
    ```

3. **Modify or create characters in App.java, send them into battle, and witness the unpredictable outcomes.**

## Races and Abilities

### 1. Humans

- **Ability:** Double Strike
  - Humans have the chance to strike the enemy twice in a single turn and deal extra damage.

### 2. Orcs

- **Ability:** Critically Strike
  - Orcs can deliver a critical strike, breaking the armor of the enemy and deal extra damage.

### 3. Casters

- **Ability:** Spellcast (Burn)
  - Casters can cast a spell that burns the enemy, and also ignores their armor.

### 4. Healers

- **Ability:** Self-Heal
  - Healers can heal themselves during battle, increasing their survivability.

## Randomized Events

The game introduces variability through the following randomized events:

- **Randomized Parries:**
  - Characters may successfully parry (dodge) enemy attacks. (20%)

- **Randomized Ability Casts:**
  - The success of abilities such as Double Strike, Critical Strike, Spellcast, or Self-Heal is randomized. (15%)

- **Randomized Damage:**
  - Damage dealt is within a specified interval, adding an element of uncertainty to each encounter.

## Contributions

Feel free to contribute to the game [here](https://github.com/iRxllz/Battle-Simulator/issues) by submitting issues or pull requests. Your ideas and improvements are welcome!

## License

This project is licensed under the [MIT License](LICENSE), so feel free to use, modify, and distribute it.

Enjoy the battles, and may the odds be in your favor!
