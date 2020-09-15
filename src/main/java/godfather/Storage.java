package godfather;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Handles the loading and saving of data onto the local storage by serialising the TaskList object
 */
public class Storage {
    // hardcoded for now:
    public static final Path ROOT_PATH = Paths.get("").toAbsolutePath();
    public static final Path DATA_DIR_PATH = Paths.get(ROOT_PATH.toAbsolutePath().toString(), "data");
    public static final Path SAVE_FILE_PATH = Paths.get(DATA_DIR_PATH.toAbsolutePath().toString(), "savedData");
    /**
     * Saves the current TaskList object by serialising it and writing onto the default file
     *
     * @param allTasks Current TaskList that represents all the tasks that have been created by the user
     *
     * @throws IOException If there are errors writing onto the intended file
     */
    public static void save(TaskList allTasks) throws IOException {
        initStorage();
        try {
            FileOutputStream fos = new FileOutputStream(SAVE_FILE_PATH.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allTasks);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Reads saved data from the default save location and returns TaskList, if it exists, or constructs a new TaskList
     * for Duke to use
     *
     * @return TaskList for Duke to use
     *
     * @throws IOException If there are errors reading from existing file or file doesn't exist
     */
    public static TaskList load() throws IOException {
        TaskList savedTasks = new TaskList();
        try {
            FileInputStream fis = new FileInputStream(SAVE_FILE_PATH.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            savedTasks = (TaskList) ois.readObject();
            ois.close();
            fis.close();
            return savedTasks;
        } catch (ClassNotFoundException | IOException e) {
            initStorage();
        }
        return savedTasks;
    }
    /**
     * Initialises the Storage location by creating the Data dir and SaveFile, if required
     *
     * @throws IOException If there are errors in file creation
     */
    private static void initStorage() throws IOException {
        createDataDir();
        createSaveFile();
        assert Files.exists(DATA_DIR_PATH) && Files.exists(SAVE_FILE_PATH) : "Storage directory/path doesn't exist";
    }
    /**
     * Creates the data directory if that path doesn't exist
     *
     * @throws IOException If there are errors in directory creation
     */
    private static void createDataDir() throws IOException {
        if (!Files.exists(DATA_DIR_PATH)) {
            Files.createDirectory(DATA_DIR_PATH);
        }
    }
    /**
     * Creates the save file if the directory exists but file doesn't
     *
     * @throws IOException If there are errors in file creation
     */
    private static void createSaveFile() throws IOException {
        assert (Files.exists(DATA_DIR_PATH)) : "data dir doesn't exist";
        if (!Files.exists(SAVE_FILE_PATH)) {
            Files.createFile(SAVE_FILE_PATH);
        }
    }
}
