package duck.storage;

import duck.exception.DuckException;
import duck.task.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * LocalStorage makes use of Serializable objects to write to a local file.
 */
public class LocalStorage implements Storage{
    private String filePath;

    /**
     * Initializes file location.
     *
     * @param filePath Location that storage file will be.
     */
    public LocalStorage(String filePath) {
        this.filePath = filePath;
    }

    private void ensureFileExists() throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            File directories = new File(file.getParentFile().getAbsolutePath());
            directories.mkdirs();
            file.createNewFile();
        }

    }


    /**
     * Serializes the TaskList object and stores in a file.
     *
     * @param taskList List of tasks to be saved.
     * @throws DuckException If file is unable to be saved due to corruption.
     */

    public void save(TaskList taskList) throws DuckException {
        try {
            ensureFileExists();
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(taskList);
            out.close();
            file.close();
        } catch (IOException i) {
            throw new DuckException("Failed to save file");
        }
    }

    /**
     * Loads the serialized data into a TaskList object.
     *
     * @return Object of instance TaskList with previously saved tasks.
     * @throws DuckException If file is not found or error occured in loading.
     */
    public TaskList load() throws DuckException {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            TaskList taskList = (TaskList) in.readObject();
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            throw new DuckException("Failed to load file");
        }
    }
}
