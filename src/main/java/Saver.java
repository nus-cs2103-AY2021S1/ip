import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Saver {
    private static final String saveFilePath = "data/duke.txt";

    public static void save(ArrayList<Task> tasks) throws IOException {
        createSaveDirectoryIfNotExists();
        FileWriter fileWriter = new FileWriter(saveFilePath);
        for (Task task: tasks) {
            fileWriter.write(task.toSaveString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public static ArrayList<Task> load() {
        try {
            createSaveDirectoryIfNotExists();
            File file = new File(saveFilePath);
            Scanner scanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                String[] components = scanner.nextLine().split("\\|");
                if (components[0].equals("T")) {
                    tasks.add(new Todo(components[2], components[1].equals("1")));
                } else if (components[0].equals("D")) {
                    tasks.add(new Deadline(components[2], components[1].equals("1"), components[3]));
                } else if (components[0].equals("E")) {
                    tasks.add(new Event(components[2], components[1].equals("1"), components[3]));
                } else {
                    throw new InvalidSaveFileException();
                }
            }
            return tasks;
        } catch (FileNotFoundException exception) {
            System.out.println("No save file detected");
            return new ArrayList<>();
        } catch (InvalidSaveFileException exception) {
            System.out.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    private static void createSaveDirectoryIfNotExists() {
        File saveDirectory = new File("data");
        if (! saveDirectory.exists()) {
            saveDirectory.mkdir();
        }
    }

    public static class InvalidSaveFileException extends Exception {
        public InvalidSaveFileException() {
            super("☹ OOPS!!! Invalid save file");
        }
    }

}
