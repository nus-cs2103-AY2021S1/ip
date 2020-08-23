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

public class Storage {

    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

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
     * Writes to save file as specified by memoryFilePath. Writing to save occurs every time the task list changes.
     * @throws IOException
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTaskList()) {
            fw.write(task.stringToSaveInMemory() + "\n");
        }
        fw.close();
    }
}
