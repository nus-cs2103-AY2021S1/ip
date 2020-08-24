import java.io.File;
import java.io.IOException;
import java.net.IDN;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Duckie {

    public static File openFile() {
        String cwd = System.getProperty("user.dir");
        Path dirPath = Paths.get(cwd, "data");
        Path filePath = Paths.get(cwd, "data", "duke.txt");
        try {
            File file = new File(String.valueOf(filePath));
            if (!file.exists()) {
                File dir = new File(String.valueOf(dirPath));
                if (!dir.exists()) {
                    if (dir.mkdirs() && file.createNewFile()) {
                        System.out.println("Memory File created successfully!");
                    } else {
                        System.out.println("Quack! Unable to create file!");
                    }
                } else {
                    if (file.createNewFile()) {
                        System.out.println("Memory File created successfully!");
                    } else {
                        System.out.println("Quack! Unable to create file!");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Quack! Encounter problem while loading data!");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        BotResponses.intro();
        BotHandler.serve();
        BotResponses.ending();
    }
}
