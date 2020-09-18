package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import duke.data.TaskList;

/**
 * Represents the file used to store task list data.
 */
public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    private static final String DEFAULT_STORAGE_FILEPATH = "src/storageData/duke.txt";

    private final String filePath;
    private final File file;

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    /**
     * @throws StorageOperationException if the default path is invalid
     */
    public Storage() throws StorageOperationException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Creates a {@code Storage}.
     * @param filePath A valid file path.
     * @throws StorageOperationException if the file cannot be created
     */
    public Storage(String filePath) throws StorageOperationException {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            if (!this.file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new StorageOperationException("Oops, an error occurred while creating a new file!");
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
