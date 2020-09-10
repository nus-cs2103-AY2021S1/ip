package cartona;

import cartona.command.Parser;
import cartona.task.TaskList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.util.List;

/**
 * The Storage class handles the saving of the TaskList to a file, and the loading of Tasks from a file.
 *
 * @author Jaya Rengam
 */
public class Storage {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    private String pathString;

    /**
     * Creates a Storage object that uses the file in the path specified.
     * @param pathString The path of the text file used.
     */
    Storage(String pathString) {
        this.pathString = pathString;
    }

    /**
     * Checks if the file located at the pathString exists. If the file does not exist, a new file will be created.
     *
     * @returns true if the file exists at the specified path, false if it does not and a new file was created.
     */
    public boolean checkAndCreateFile() throws IOException {
        File file = new File(pathString);
        boolean isCreated = file.exists();

        if (!isCreated) {
            file.createNewFile();
        }

        return isCreated;
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
                    System.out.printf(HORIZONTAL_LINE + "     Found an existing list at %s%n" + HORIZONTAL_LINE,
                                            path);
                } else {
                    System.out.printf(HORIZONTAL_LINE + "     Found an existing list, but it was empty!%n"
                            + HORIZONTAL_LINE);
                }

                // Parse each line of text and add it to the TasKList
                for (String taskLine : contents) {
                    taskList.addTask(parser.parseFromStorage(taskLine));
                }
            } else {
                System.out.printf(HORIZONTAL_LINE + "     Existing list not found, creating new list\n"
                        + HORIZONTAL_LINE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }

    public String getPath() {
        return pathString;
    }

    /**
     * Saves the given TaskList to the pre-assigned text file.
     * @param taskList The TaskList to be saved.
     */
    public void saveListToFile(TaskList taskList) {
        String stringToWrite = taskList.getListForStorage();

        Path listFilePath = Path.of(pathString);
        System.out.printf("writing file %s", listFilePath.toAbsolutePath().toString());
        try (BufferedWriter writer = Files.newBufferedWriter(
                listFilePath, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
            writer.write(stringToWrite, 0, stringToWrite.length());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
