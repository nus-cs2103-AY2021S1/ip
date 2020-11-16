package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exception.DukeException;
import task.tasks.Deadline;
import task.tasks.Event;
import task.tasks.Task;
import task.tasks.Todo;

/**
 * Deals with loading tasks from duke.txt and saving tasks in duke.txt.
 */
public class Storage {
    /**
     * Folder path of folder which duke.txt is found in.
     */
    protected static final String FOLDER_PATH =
            java.nio.file.Paths.get(System.getProperty("user.dir"), "data").toString();
    /**
     * File path of duke.txt.
     */
    protected static final String FILE_PATH =
            java.nio.file.Paths.get(System.getProperty("user.dir"), "data", "duke.txt").toString();

    /**
     * Creates a folder path for duke.txt if there isn't a folder for duke.txt.
     */
    public static String createFolder() {
        try {
            File writingDirectory = new File(Storage.FOLDER_PATH);

            // If src\main\java\data folder does not exist, create one
            if (!writingDirectory.exists()) {
                writingDirectory.mkdir();
            }
            return "";
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
            return DukeException.printErrorCreatingFolder();
        }
    }

    /**
     * Appends text to duke.txt.
     *
     * @param textToAdd Text to be appended to duke.txt.
     */
    public static String appendToFile(String textToAdd) {
        try {
            // Create src\main\java\data folder if needed
            Storage.createFolder();

            // Append instead of overwriting
            FileWriter fw = new FileWriter(Storage.FILE_PATH, true);
            fw.write(textToAdd + "\n");
            fw.close();
            return "";
        } catch (IOException e) {
            System.out.println("Error Message: " + e.getMessage());
            return DukeException.printErrorAppendingToFile();
        }
    }

    /**
     * Amends text in duke.txt.
     *
     * @param currentText Targeted text to be amended.
     * @param amendedText New text written in place of targeted text.
     */
    public static String amendFile(String currentText, String amendedText) {
        try {
            File file = new File(Storage.FILE_PATH);

            // Replace currentText with amendedText
            List<String> out = Files.lines(file.toPath())
                    .map(line -> line.replace(currentText, amendedText))
                    .collect(Collectors.toList());

            // TRUNCATE_EXISTING overwrites our existing text file and rewrites the text file
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            return "";
        } catch (IOException e) {
            System.out.println("Error Message: " + e.getMessage());
            return DukeException.printErrorAmendingFile();
        }
    }

    /**
     * Deletes text in duke.txt.
     *
     * @param textToDelete Targeted text to be deleted.
     */
    public static String deleteFromFile(String textToDelete) {
        try {
            File file = new File(Storage.FILE_PATH);

            // Use Stream filter to remove line that contains textToDelete
            List<String> out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(textToDelete))
                    .collect(Collectors.toList());

            // TRUNCATE_EXISTING replaces old contents of duke.txt file
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            return "";
        } catch (IOException e) {
            System.out.println("Error Message: " + e.getMessage());
            return DukeException.printErrorDeletingTextFromFile();
        }
    }

    /**
     * Pulls all task data inside duke.txt into Kim Jong Duke's task list upon starting application.
     *
     * @return List of tasks.
     */
    public static List<Task> loadTextIntoTaskList() {
        List<String> lines;
        List<Task> tasks = new ArrayList<>();
        File file = new File(Storage.FILE_PATH);
        try {
            // E.g [D][Y] return book (by: Sunday)
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (String line : lines) {
                // E.g [[D][Y], return book (by: Sunday)]
                String[] parts = line.split(" ", 2);
                assert parts.length == 2 : "Something went wrong while loading text into task list.";

                // E.g [D][Y]
                String prefix = parts[0];

                // E.g return book (by: Sunday)
                String description = parts[1];

                Task task;
                if (prefix.contains("[T]")) {
                    task = new Todo(description);
                    if (prefix.contains("[Y]")) {
                        task.markAsDone();
                    }
                } else if (prefix.contains("[D]")) {
                    // Take words before brackets
                    String agenda = description.substring(0, description.lastIndexOf(" ("));

                    // Grab "by: Sunday" in "(by: Sunday)"
                    // lastIndexOf ensures that something like
                    // return book (OK will do so) (by: Sunday) will not throw error
                    String bracketText = description.substring(
                            description.lastIndexOf("(") + 1, description.lastIndexOf(")"));

                    // Grab "Sunday"
                    String by = bracketText.split("by: ", 2)[1];
                    task = new Deadline(agenda, by);
                    if (prefix.contains("[Y]")) {
                        task.markAsDone();
                    }
                } else { // prefix.contains("[E]"))
                    assert prefix.contains("[E]") : "Prefix does not contain [E]";
                    // Take words before brackets
                    String agenda = description.substring(0, description.lastIndexOf(" ("));

                    // Grab "at: Mon 2-4pm"
                    // lastIndexOf ensures that something like
                    // return book (OK will do so) (at: Mon 2-4pm) will not throw error
                    String bracketText = description.substring(
                            description.lastIndexOf("(") + 1, description.lastIndexOf(")"));

                    // Grab "Mon 2-4pm"
                    String at = bracketText.split("at: ", 2)[1];
                    task = new Event(agenda, at);
                    if (prefix.contains("[Y]")) {
                        task.markAsDone();
                    }
                    assert !prefix.contains("[Y]") : "Prefix contains [Y]";
                }
                // Add task to list of task
                tasks.add(task);
            }
        } catch (IOException e) {
            DukeException.printErrorLoadingTextIntoTaskList();
            System.out.println("Error Message: " + e.getMessage());
        }
        return tasks;
    }
}
