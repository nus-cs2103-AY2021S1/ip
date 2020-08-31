import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * The Storage class handles the saving of the TaskList to a file, and the loading of Tasks from a file.
 */
public class Storage {
    private static String horizontalLine = "    ____________________________________________________________\n";

    private String pathString;

    /**
     * Creates a Storage object that uses the file in the path specified.
     * @param pathString The path of the text file used.
     */
    Storage(String pathString) {
        this.pathString = pathString;
    }

    /**
     * Gets the list of Tasks, as a TaskList, from the (pre-assigned) text file.
     * If the file does not exist, it returns an empty TaskList.
     */
    public TaskList getListFromStorage() {
        // Create File object
        Path path = Path.of(pathString);
        File savedListFile = new File(pathString);

        TaskList taskList = new TaskList();
        Parser parser = new Parser();

        try {
            // Check if the file exists
            boolean doesSavedListExist = savedListFile.createNewFile();

            if (!doesSavedListExist) {
                // If the file exists, read its contents
                List<String> contents = Files.readAllLines(path, StandardCharsets.UTF_8);
                if (contents.size() > 0) {
                    System.out.printf(horizontalLine + "     Found an existing list at %s%n" + horizontalLine,
                                            path);
                } else {
                    System.out.printf(horizontalLine + "     Found an existing list, but it was empty!%n" +
                                        horizontalLine);
                }

                // Parse each line of text and add it to the TasKList
                for (String taskLine : contents) {
                    taskList.addTask(parser.parseFromStorage(taskLine));
                }
            } else {
                System.out.printf(horizontalLine + "     Existing list not found, creating new list\n" + horizontalLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (IndexOutOfBoundsException e) {
            // FOR TESTING, TO BE DISCARDED BEFORE RELEASE
            System.out.println("     Encoding error: creating new list");
            taskList = new TaskList();
            Path listFilePath = Path.of(pathString);

            try {
                new PrintWriter(pathString).close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }

        return taskList;
    }

    /**
     * Saves the given TaskList to the pre-assigned text file.
     * @param taskList The TaskList to be saved.
     */
    public void saveListToFile(TaskList taskList) {
        String stringToWrite = taskList.getListForStorage();

        Path listFilePath = Path.of(pathString);

        try (BufferedWriter writer = Files.newBufferedWriter(
                listFilePath, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
            writer.write(stringToWrite, 0, stringToWrite.length());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}