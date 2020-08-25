import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
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
                switch (components[0]) {
                    case "T":
                        tasks.add(new Todo(components[2], components[1].equals("1")));
                        break;
                    case "D":
                        tasks.add(new Deadline(components[2], components[1].equals("1"), LocalDateTime.parse(components[3])));
                        break;
                    case "E":
                        tasks.add(new Event(components[2], components[1].equals("1"), components[3]));
                        break;
                    default:
                        throw new InvalidSaveFileException();
                }
            }
            return tasks;
        } catch (FileNotFoundException exception) {
            System.out.println("No save file detected");
            return new ArrayList<>();
        } catch (InvalidSaveFileException | DateTimeParseException exception) {
            Ui.printExceptionBetweenLines(exception);
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
            super("Invalid save file");
        }
    }

}
