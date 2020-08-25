package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Loads tasks from and saves tasks to the save file (a saved task list). Storage will attempt to load the save file
 * upon start up of the program and will from thereon overwrite and save the file every time the task list changes.
 */
public class Storage {

    /** The relative path in which the save file is located */
    private String filePath;

    /**
     * Creates and initializes the Storage object.
     *
     * @param filePath The relative path in which the save file is located.
     */
    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the save file upon start up of the program. If no valid save file is found upon start
     * up, a new save file will be created.
     *
     * @return A list of strings that represents tasks, that the TaskList class can then convert into a list of
     * tasks.
     * @throws FileNotFoundException If from the path specified by filePath, a save file is not found or there is a
     * missing directory / there are missing directories.
     */
    public List<String> load() throws FileNotFoundException {
        File memoryFile = new File(filePath);
        if (!memoryFile.exists()) { // If file is non-existent, either the .txt file or a directory in the
            // filePath does not exist
            boolean areExistentDirectories = true; // Check if all directories specified in filePath exist
            String[] splitPath = filePath.split("/");
            String testPath = "";
            String nonExistentDirectory = "";
            for (int i = 0; i < splitPath.length - 1; i++) {
                testPath += splitPath[i] + "/";
                if (!new File(testPath).exists()) { // A directory specified in filePath does not exist
                    areExistentDirectories = false;
                    nonExistentDirectory = splitPath[i]; // The directory specified in filePath that does not
                    // exist
                    break;
                }
            }
            if (areExistentDirectories) { // All directories specified in memoryFilePath exists, only the .txt file
                // does not exist
                try {
                    Files.createFile(Path.of(filePath)); // Create the .txt file with location as stated in
                    // filePath
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                throw new FileNotFoundException("ERROR: Could not load last save.\nThe save file \""
                        + splitPath[splitPath.length - 1] + "\" does not exist.\nNow loading a new, empty task list" +
                        ".\nA new save file \"" + splitPath[splitPath.length - 1] + "\" has been created with the\n" +
                        "following path: \"" + filePath + "\".");
            } else { // A directory specified in filePath does not exist so we should create it (and all its
                // subdirectories if any)
                try {
                    String fullPath = "";
                    for (int i = 0; i < splitPath.length - 1; i++) {
                        fullPath += splitPath[i] + "/";
                    }
                    Files.createDirectories(Path.of(fullPath)); // Create missing directory (and all its
                    // subdirectories if any) as specified by filePath
                    Files.createFile(Path.of(filePath)); // Create .txt file in newly created path
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                throw new FileNotFoundException("ERROR: Could not load last save.\nPath specified for save file: " +
                        "\"" + filePath + "\"\nThe directory \"" + nonExistentDirectory + "\"\n(and hence all " +
                        "subdirectories of it, if any)\ndoes not exist.\nNow loading a new, empty task list.\nA new " +
                        "save file \"" + splitPath[splitPath.length - 1] + "\" has been created with the\nfollowing " +
                        "path: \"" + filePath + "\".");
            }
        }

        List<String> result = new ArrayList<>();

        // If the save file exists, we load the task list with tasks as specified in the save file
        Scanner sc = new Scanner(memoryFile);
        while (sc.hasNextLine()) {
            result.add(sc.nextLine());
        }

        return result;
    }

    /**
     * Writes to save file as specified by filePath. Writing to save occurs every time the task list changes.
     *
     * @throws IOException If an error occurs during the process of trying to write to the save file.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTaskList()) {
            fw.write(task.stringToSaveInMemory() + "\n");
        }
        fw.close();
    }
}
