import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManipulator {
    protected final static String FOLDERPATH = java.nio.file.Paths.get(System.getProperty("user.dir"), "data").toString();
    protected final static String FILEPATH = System.getProperty("user.dir").endsWith("text-ui-test")
            ? java.nio.file.Paths.get(System.getProperty("user.dir"), "data", "duke.txt").toString()
            : java.nio.file.Paths.get(System.getProperty("user.dir"), "data", "duke.txt").toString();

    public static void createFolder() {
        try {
            File writingDirectory = new File(FileManipulator.FOLDERPATH);
            // If src\main\java\data folder does not exist, create one
            if (!writingDirectory.exists()) {
                writingDirectory.mkdir();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while creating src\\main\\java\\data folder " + e.getMessage());
        }
    }

    public static void appendToFile(String textToAdd) {
        try {
            // Create src\main\java\data folder if needed
            FileManipulator.createFolder();
            // Append instead of overwriting
            FileWriter fw = new FileWriter(FileManipulator.FILEPATH, true);
            fw.write(textToAdd + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to file: " + e.getMessage());
        }
    }

    public static void amendFile(String currentText, String amendedText) {
        try {
            File file = new File(FileManipulator.FILEPATH);
            // Replace currentText with amendedText
            List<String> out = Files.lines(file.toPath())
                    .map(line -> line.replace(currentText, amendedText))
                    .collect(Collectors.toList());
            // TRUNCATE_EXISTING overwrites our existing text file and rewrites the text file
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Something went wrong while amending text to file: " + e.getMessage());
        }
    }

    public static void deleteFromFile(String textToDelete) {
        try {
            File file = new File(FileManipulator.FILEPATH);
            // Use Stream filter to remove line that contains textToDelete
            List<String> out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(textToDelete))
                    .collect(Collectors.toList());
            // TRUNCATE_EXISTING replaces old contents of duke.txt file
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Something went wrong while deleting text from file: " + e.getMessage());
        }
    }

    public static List<Task> loadTextIntoTaskList() {
        List<String> lines;
        List<Task> tasks = new ArrayList<>();
        File file = new File(FileManipulator.FILEPATH);
        try {
            // E.g [D][Y] return book (by: Sunday)
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (String line : lines) {
                // E.g [[D][Y], return book (by: Sunday)]
                String[] parts = line.split(" ", 2);
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
                    // lastIndexOf ensures that something like return book (OK will do so) (by: Sunday) will not throw error
                    String bracketText = description.substring(description.lastIndexOf("(") + 1, description.lastIndexOf(")"));
                    // Grab "Sunday"
                    String by = bracketText.split("by: ", 2)[1];
                    task = new Deadline(agenda, by);
                    if (prefix.contains("[Y]")) {
                        task.markAsDone();
                    }
                } else { // prefix.contains("[E]"))
                    // Take words before brackets
                    String agenda = description.substring(0, description.lastIndexOf(" ("));
                    // Grab "at: Mon 2-4pm"
                    // lastIndexOf ensures that something like return book (OK will do so) (at: Mon 2-4pm) will not throw error
                    String bracketText = description.substring(description.lastIndexOf("(") + 1, description.lastIndexOf(")"));
                    // Grab "Mon 2-4pm"
                    String at = bracketText.split("at: ", 2)[1];
                    task = new Event(agenda, at);
                    if (prefix.contains("[Y]")) {
                        task.markAsDone();
                    }
                }
                // Add task to list of task
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("-- IGNORE THIS ERROR IF IT IS YOUR FIRST TIME USING THE APPLICATION AND HAVE NOT ADD ANYTHING INTO TASK LIST! --");
            System.out.println("-- Error is thrown because data\\duke.txt\\ has not been created yet on first run. --");
            System.out.println("Something went wrong while loading duke.txt into task list: " + e.getMessage());
        }
        return tasks;
    }
}