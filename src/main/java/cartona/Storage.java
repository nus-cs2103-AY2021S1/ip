package cartona;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

import cartona.command.Parser;
import cartona.task.TaskList;

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
    public Storage(String pathString) {
        this.pathString = pathString;
    }

    /**
     * Checks if the file located at the pathString exists. If the file does not exist, a new file will be created.
     *
     * @returns true if the file exists at the specified path, false if it does not and a new file was created.
     * @throws IOException if there is an error in creating the file
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
     *
     * @return the TaskList containing the Tasks recorded in the text file.
     */
    public TaskList getListFromStorage() {
        // Create File object
        Path path = Path.of(pathString);
        TaskList taskList = new TaskList();
        Parser parser = new Parser();

        try {
            //Scanner sc = new Scanner(new File(pathString));

            // Read the contents of the file
            List<String> contents = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Parse each line of text and add it to the TaskList
            for (String taskLine : contents) {
                if (taskLine.equals("")) {
                    continue;
                }
                taskList.addTask(parser.parseFromStorage(taskLine));
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
     *
     * @param taskList The TaskList to be saved.
     */
    public void saveListToFile(TaskList taskList) {
        try {
            assert checkAndCreateFile();
        } catch (IOException e) {
            // accounts for case where parent directory of file does not exist
            throw new AssertionError("File should have been created");
        }

        String stringToWrite = taskList.getListForStorage();

        try {
            FileWriter writer = new FileWriter(pathString, StandardCharsets.UTF_8);
            writer.write(stringToWrite, 0, stringToWrite.length());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
