package viscount;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import viscount.exception.ViscountSaveDataException;
import viscount.task.Task;

/**
 * Represents Viscount's storage.
 *
 * Handles loading tasks from data file and writing tasks to the file.
 */
public class Storage {
    private static final String DATA_FILE_NAME = "viscount.txt";

    private String dataDirectoryPath;
    private String filePathString;

    /**
     * Instantiates a new storage object.
     *
     * @param dataDirectoryPath Directory path to the data file.
     */
    public Storage(String dataDirectoryPath) {
        this.dataDirectoryPath = dataDirectoryPath;
        this.filePathString = dataDirectoryPath + DATA_FILE_NAME;
    }

    /**
     * Saves task list data to disk.
     *
     * @param tasks Task list saved.
     * @throws ViscountSaveDataException If exception occurs when writing to disk.
     */
    public void saveToDisk(List<Task> tasks) throws ViscountSaveDataException {
        Path filePath = Paths.get(filePathString);
        List<String> savedData = new ArrayList<>();

        for (Task task : tasks) {
            savedData.add(task.toTaskData());
        }

        try {
            Files.write(filePath, savedData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ViscountSaveDataException();
        }
    }

    /**
     * Loads task list data from disk.
     *
     * @return Task list loaded.
     * @throws IOException If exception occurs when loading from disk or creating a new data file.
     */
    public List<Task> loadFromDisk() throws IOException {
        if (!hasDataDirectory()) {
            createDataDirectory();
        }

        if (hasExistingDataFile()) {
            return convertTaskDataToTasks();
        } else {
            return createNewDataFile();
        }
    }

    /**
     * Converts task data in the data file to a list of tasks.
     *
     * @return List of tasks represented by the task data
     * @throws IOException If exception occurs when reading from data file.
     */
    private List<Task> convertTaskDataToTasks() throws IOException {
        File dataFile = new File(filePathString);
        Scanner sc = new Scanner(dataFile);
        List<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();

            if (!line.isEmpty()) {
                tasks.add(Parser.parseTaskData(line));
            }
        }

        return tasks;
    }

    /**
     * Creates a new data file.
     *
     * @return An empty list of tasks representing the new data file with no task data saved.
     * @throws IOException If an error occurs while creating the new data file.
     */
    private List<Task> createNewDataFile() throws IOException {
        Path filePath = Paths.get(filePathString);
        Files.write(filePath, new ArrayList<String>(), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        return new ArrayList<>();
    }

    private boolean hasDataDirectory() {
        File directory = new File(dataDirectoryPath);
        return directory.exists();
    }

    private void createDataDirectory() {
        File directory = new File(dataDirectoryPath);
        directory.mkdir();
    }

    private boolean hasExistingDataFile() {
        Path filePath = Paths.get(filePathString);
        return Files.exists(filePath);
    }
}
