package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.data.TaskList;
import duke.data.exception.IllegalValueException;

/**
 * Represents the file used to store task list data.
 */
public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    private static final String DEFAULT_STORAGE_FILEPATH = "src/storageData/duke.txt";

    private String filePath;
    private File file;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");

    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Creates a {@code Storage}.
     * @param filePath A valid file path.
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!this.file.exists()) {
            throw new InvalidStorageFilePathException("File not found.");
        }
    }

    /**
     * Loads the {@code TaskList} data from this storage file, and then returns it.
     * Returns an empty {@code TaskList} if the file does not exist, or is not a regular file.
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public TaskList load() throws StorageOperationException, FileNotFoundException {
        if (!this.file.exists()) {
            return new TaskList();
        }
        return TaskListDecoder.decodeTaskList(this.file);
    }

    /**
     * Saves the {@code taskList} data to the storage file.
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(TaskList taskList) throws StorageOperationException {
        assert new File(this.filePath).exists();
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder textToAdd = new StringBuilder();
            encodedTaskList.forEach(encodedTask -> textToAdd.append(encodedTask).append("\n"));
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + filePath);
        }
    }

}
