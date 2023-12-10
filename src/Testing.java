import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class CharacterWrapper {
    public Character character;

    public CharacterWrapper(Character character) {
        this.character = character;
    }
}

public class Testing {

    static int knightWinCount = 0, archerWinCount = 0, ogreWinCount = 0, goblinWinCount = 0, warlockWinCount = 0,
            shamanWinCount = 0,
            monkWinCount = 0, priestWinCount = 0;

    static File file = new File("TestingData.txt");

    public static void main(String[] args) throws Exception {
        double startTime = System.currentTimeMillis();

        int numberOfRuns = 500;
        getGameStatistics(numberOfRuns);

        double stopTime = System.currentTimeMillis();
        System.out
                .println("Elapsed time for " + numberOfRuns + " tests: " + (stopTime - startTime) / 1000 + " seconds.");
    }

    private static void getGameStatistics(int numberOfRuns) throws IOException, InterruptedException {

        runAndWriteDataToFile(numberOfRuns);
        readDataFromFile();
        writeStatisticsToFile(numberOfRuns);

    }

    private static void runAndWriteDataToFile(int numberOfRuns) throws IOException, InterruptedException {
        try (FileWriter writer = new FileWriter(file)) {
            CharacterWrapper[] fighters = {
                    new CharacterWrapper(App.knight),
                    new CharacterWrapper(App.warlock),
                    new CharacterWrapper(App.ogre),
                    new CharacterWrapper(App.monk),
                    new CharacterWrapper(App.archer),
                    new CharacterWrapper(App.shaman),
                    new CharacterWrapper(App.goblin),
                    new CharacterWrapper(App.priest)
            };

            int i;
            for (i = 0; i < numberOfRuns; i++) {
                Character winnerQF1 = App.fight(App.knight, App.warlock);
                Character winnerQF2 = App.fight(App.ogre, App.monk);
                Character winnerQF3 = App.fight(App.archer, App.shaman);
                Character winnerQF4 = App.fight(App.goblin, App.priest);

                Character winnerSF1 = App.fight(winnerQF1, winnerQF3);
                Character winnerSF2 = App.fight(winnerQF2, winnerQF4);

                Character finalWinner = App.fight(winnerSF1, winnerSF2);

                writer.write(finalWinner.getClass().getName() + "\n");

                swap(fighters[0], fighters[1]);
                swap(fighters[2], fighters[3]);
                swap(fighters[4], fighters[5]);
                swap(fighters[6], fighters[7]);

                swap(fighters[1], fighters[2]);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void swap(CharacterWrapper char1, CharacterWrapper char2) {
        Character temp = char1.character;
        char1.character = char2.character;
        char2.character = temp;
    }

    private static void readDataFromFile() throws FileNotFoundException {
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String className = reader.nextLine();
                switch (className) {
                    case "Knight":
                        knightWinCount++;
                        break;
                    case "Archer":
                        archerWinCount++;
                        break;
                    case "Ogre":
                        ogreWinCount++;
                        break;
                    case "Goblin":
                        goblinWinCount++;
                        break;
                    case "Warlock":
                        warlockWinCount++;
                        break;
                    case "Shaman":
                        shamanWinCount++;
                        break;
                    case "Monk":
                        monkWinCount++;
                        break;
                    case "Priest":
                        priestWinCount++;
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeStatisticsToFile(int numberOfRuns) throws IOException {
        File statisticsFile = new File("Statistics.txt");
        try (FileWriter statisticsWriter = new FileWriter(statisticsFile)) {
            statisticsWriter
                    .write("The numbers represent how many times a character won the tournament - (" + numberOfRuns
                            + " TESTS RUN)\n");
            statisticsWriter.write("Knight: " + knightWinCount + "\n");
            statisticsWriter.write("Archer: " + archerWinCount + "\n");
            statisticsWriter.write("Ogre: " + ogreWinCount + "\n");
            statisticsWriter.write("Goblin: " + goblinWinCount + "\n");
            statisticsWriter.write("Warlock: " + warlockWinCount + "\n");
            statisticsWriter.write("Shaman: " + shamanWinCount + "\n");
            statisticsWriter.write("Monk: " + monkWinCount + "\n");
            statisticsWriter.write("Priest: " + priestWinCount + "\n");
            statisticsWriter.write("\nAverage for " + numberOfRuns + " tests should be: " + numberOfRuns / 8);
            statisticsWriter.write("\nRESULTS MAY VARY!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}