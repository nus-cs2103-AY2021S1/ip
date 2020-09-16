package tickbot.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;
import tickbot.exception.CorruptedDataException;
import tickbot.task.Deadline;
import tickbot.task.Event;
import tickbot.task.Task;
import tickbot.task.Todo;

/**
 * The class to represent the data storage.
 */
public class DataStorage {
    private static final String DATA_STORAGE_FILE_NAME = "tasks.csv";

    /**
     * Reads the stored data from the application data directory.
     * @return A list containing the stored tasks.
     * @see DataStorage#getDataStoragePath
     */
    public List<Task> read() {
        List<Task> tasks = new ArrayList<>();
        try {
            File dataFile = new File(getDataStoragePath(), DATA_STORAGE_FILE_NAME);
            dataFile.getParentFile().mkdirs(); // create the data directory if not exists
            dataFile.createNewFile(); // create the file if not exists
            FileInputStream inputStream = new FileInputStream(dataFile);
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            for (String[] parts : reader) {
                Task task;
                boolean completed = Objects.equals(parts[1], "1");
                switch (parts[0]) {
                case "T": {
                    task = new Todo(completed, parts[3]);
                    break;
                }
                case "E": {
                    LocalDateTime time = LocalDateTime.parse(parts[4]);
                    task = new Event(completed, parts[3], time);
                    break;
                }
                case "D": {
                    LocalDateTime time = LocalDateTime.parse(parts[4]);
                    task = new Deadline(completed, parts[3], time);
                    break;
                }
                default: {
                    throw new CorruptedDataException();
                }
                }
                String[] tags = parts[2].split("#");
                for (String tag : tags) {
                    task.addTag(tag);
                }
                tasks.add(task);
            }
        } catch (SecurityException | IOException err) {
            System.err.println("Warning: unable to read data storage. "
                    + "Your task list is not loaded.");
        } catch (DateTimeException | CorruptedDataException | IndexOutOfBoundsException err) {
            System.err.println("Warning: Corrupted data storage. "
                    + "Your task list may not be fully loaded.");
        }
        return tasks;
    }

    /**
     * Writes the task list into the application data directory.
     * @param tasks The task list to write.
     * @see DataStorage#getDataStoragePath
     */
    public void update(List<Task> tasks) {
        assert tasks != null; // task list cannot be null
        try {
            File dataFile = new File(getDataStoragePath(), DATA_STORAGE_FILE_NAME);
            FileOutputStream outputStream = new FileOutputStream(dataFile);
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream));
            for (Task task : tasks) {
                String[] line = new String[] {
                    task.getTaskType(),
                    task.isCompleted() ? "1" : "0",
                    String.join("#", task.getTags()),
                    task.getContent(),
                    Objects.toString(task.getTime()) // time could be null
                };
                writer.writeNext(line);
            }
            writer.close();
        } catch (SecurityException | IOException err) {
            System.err.println("Warning: unable to write to data storage. "
                + "Your task list is not saved.");
        }
    }

    /**
     * Gets the path of the application data directory.
     * <p> The path is OS-specified and can vary among different OS. </p>
     * @return The path of the application data directory.
     */
    private static String getDataStoragePath() {
        AppDirs appDirs = AppDirsFactory.getInstance();
        return appDirs.getUserDataDir("tickbot", null, "HouRui");
    }
}
