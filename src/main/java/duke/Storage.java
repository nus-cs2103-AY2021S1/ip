package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class for loading and persisting data to disk.
 */
public class Storage {
    private static final String saveFilePath = "data/duke.txt";

    /**
     * Saves tasks to disk.
     * @param tasks ArrayList of tasks to save
     * @throws IOException if cannot write to saveFilePath
     */
    public static void save(ArrayList<Task> tasks) throws IOException {
        createSaveDirectoryIfNotExists();
        FileWriter fileWriter = new FileWriter(saveFilePath);
        for (Task task: tasks) {
            fileWriter.write(task.toSaveString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Loads tasks from saveFilePath.
     * @return ArrayList of loaded tasks
     */
    public static ArrayList<Task> load() throws InvalidSaveFileException, DateTimeParseException,
            FileNotFoundException {
        createSaveDirectoryIfNotExists();
        File file = new File(saveFilePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        final String doneString = "1";
        while (scanner.hasNext()) {
            String[] components = scanner.nextLine().split("\\|");
            switch (components[0]) {
            case "T":
                tasks.add(new Todo(components[2], components[1].equals(doneString)));
                break;
            case "D":
                tasks.add(new Deadline(components[2], components[1].equals(doneString),
                        LocalDateTime.parse(components[3])));
                break;
            case "E":
                tasks.add(new Event(components[2], components[1].equals(doneString),
                        components[3]));
                break;
            default:
                throw new InvalidSaveFileException();
            }
        }
        return tasks;
    }

    private static void createSaveDirectoryIfNotExists() {
        File saveDirectory = new File("data");
        if (!saveDirectory.exists()) {
            saveDirectory.mkdir();
        }
    }

    /**
     * Exception when save file syntax is invalid.
     */
    public static class InvalidSaveFileException extends Exception {
        public InvalidSaveFileException() {
            super("Invalid save file");
        }
    }

}
