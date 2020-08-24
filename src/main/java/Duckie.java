import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Duckie {

    public static File openFile() {
        String cwd = System.getProperty("user.dir");
        Path dirPath = Paths.get(cwd, "data");
        Path filePath = Paths.get(cwd, "data", "duckie.txt");
        try {
            File duckieFile = new File(String.valueOf(filePath));
            if (!duckieFile.exists()) {
                File dir = new File(String.valueOf(dirPath));
                if (!dir.exists()) {
                    if (dir.mkdirs() && duckieFile.createNewFile()) {
                        System.out.println("Memory File created successfully!");
                    } else {
                        System.out.println("Quack! Unable to create file!");
                    }
                } else {
                    if (duckieFile.createNewFile()) {
                        System.out.println("Memory File created successfully!");
                    } else {
                        System.out.println("Quack! Unable to create file!");
                    }
                }
            }
            return duckieFile;
        } catch (IOException e) {
            System.out.println("Quack! Encounter problem while loading data!");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        BotResponses.intro();
        File duckieFile = openFile();
        try {
            BotHandler.processData(new Scanner(duckieFile));
        } catch (IOException e) {
            System.out.println("Quack! Encounter problem while loading data!");
        } catch (DuckieException e ) {
            System.out.println(e.getMessage());
        }
        BotHandler.serve();
        BotResponses.ending();
    }
}
