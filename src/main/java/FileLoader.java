import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLoader {
    private static final String directoryPath = "data";
    private static final String filePath = "data/duke.txt";

    public static void readSavedFile(ArrayList<Task> tasks) {
        try {
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                System.out.println("There is not such a directory, created one for you!");
                directory.mkdir();
            }

            File f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println("Can't find the file. You can add a file on your own.");
            } else {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] store = line.split(" ", 2);
                    Duke.getTasks().add(new Task(store[1]));    
                }
                sc.close();
                System.out.println("All the data has been loaded!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : Duke.getTasks()) {
                String[] store = task.toString().split(" ");
                writer.write(store[0] + " " + store[1] + " " + store[2] + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}