package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

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

        // If file is non-existent, either the .txt file or a directory in the filePath does not exist
        if (!memoryFile.exists()) {
            handleMemoryFileDoesNotExist();
        }

        // Else the save file exists and we load the task list with tasks as specified in the save file
        List<String> tasks = new ArrayList<>();
        Scanner sc = new Scanner(memoryFile);
        while (sc.hasNextLine()) {
            tasks.add(sc.nextLine());
        }
        return tasks;
    }

    private void handleMemoryFileDoesNotExist() throws FileNotFoundException {
        if (areAllExistentDirectories()) { // All directories specified in memoryFilePath exists, only the .txt file
            // does not exist
            createMissingSaveFile();
        } else { // A directory specified in filePath does not exist so we should create it (and all its subdirectories
            // if any)
            createMissingDirectoriesAndSaveFile();
        }
    }

    private boolean areAllExistentDirectories() {
        boolean areAllExistentDirectories = true; // Check if all directories specified in filePath exist
        String[] pathParts = filePath.split("/");
        String testPath = "";
        for (int i = 0; i < pathParts.length - 1; i++) {
            testPath += pathParts[i] + "/";
            if (!new File(testPath).exists()) { // A directory specified in filePath does not exist
                areAllExistentDirectories = false;
                break;
            }
        }
        return areAllExistentDirectories;
    }

    private String findNonExistentDirectory() {
        String[] pathParts = filePath.split("/");
        String testPath = "";
        for (int i = 0; i < pathParts.length - 1; i++) {
            testPath += pathParts[i] + "/";
            if (!new File(testPath).exists()) { // A directory specified in filePath does not exist
                return pathParts[i]; // The directory specified in filePath that does not exist
            }
        }
        assert false : "All directories exist. This method was wrongly called.";
        return "";
    }

    private void createMissingSaveFile() throws FileNotFoundException {
        try {
            Files.createFile(Path.of(filePath)); // Create the .txt file with location as stated in filePath
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        String[] pathParts = filePath.split("/");
        throw new FileNotFoundException("Error encountered: Could not load last save.\nThe save file \""
                + pathParts[pathParts.length - 1] + "\" does not exist.\nNow loading a new, empty task list"
                + ".\nA new save file \"" + pathParts[pathParts.length - 1] + "\" has been created with the\n"
                + "following path: \"" + filePath + "\".");
    }

    private void createMissingDirectoriesAndSaveFile() throws FileNotFoundException {
        String nonExistentDirectory = findNonExistentDirectory();
        try {
            String pathOfDirectoriesToCreate = buildPathOfDirectoriesToCreate();
            Files.createDirectories(Path.of(pathOfDirectoriesToCreate)); // Create missing directory (and all its
            // subdirectories if any) as specified by filePath
            Files.createFile(Path.of(filePath)); // Create .txt file in newly created path
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        String[] pathParts = filePath.split("/");
        throw new FileNotFoundException("Error encountered: Could not load last save.\nPath specified for save "
                + "file: "
                + "\"" + filePath + "\"\nThe directory \"" + nonExistentDirectory + "\"\n(and hence all "
                + "subdirectories of it, if any)\ndoes not exist.\nNow loading a new, empty task list.\nA new "
                + "save file \"" + pathParts[pathParts.length - 1] + "\" has been created with the\nfollowing "
                + "path: \"" + filePath + "\".");
    }

    private String buildPathOfDirectoriesToCreate() {
        String[] pathParts = filePath.split("/");
        String pathOfDirectoriesToCreate = "";
        for (int i = 0; i < pathParts.length - 1; i++) {
            pathOfDirectoriesToCreate += pathParts[i] + "/";
        }
        return pathOfDirectoriesToCreate;
    }

    /**
     * Writes to save file as specified by filePath. Writing to save occurs every time the task list changes.
     *
     * @throws IOException If an error occurs during the process of trying to write to the save file.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTaskList()) {
            fw.write(task.toStringForMemory() + "\n");
        }
        fw.close();
    }
}
