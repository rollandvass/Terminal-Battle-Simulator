import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
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
            statisticsWriter.write("\nAverage should be around " + numberOfRuns / 8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}