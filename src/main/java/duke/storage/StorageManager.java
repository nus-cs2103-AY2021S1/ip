package duke.storage;

import java.io.File;

import duke.exception.DukeOperationException;
import duke.list.ExpenseList;
import duke.list.TaskList;

/** Manages the different <code>Storages</code> of Duke. */
public class StorageManager {
    private static final String DEFAULT_FILEPATH = "data/";

    private final String path;
    private final TaskStorage taskStorage;
    private final ExpenseStorage expenseStorage;

    /**
     * Constructor method.
     *
     * @param path the directory for the text files to be saved into.
     */
    private StorageManager(String path) {
        this.path = path;
        this.taskStorage = new TaskStorage(path);
        this.expenseStorage = new ExpenseStorage(path);
    }

    /**
     * Creates a <code>StorageManager</code>.
     * The directory to the files can be predetermined. If not, the default will be used.
     * If the path directory does not exist, it will be created.
     *
     * @param path the directory for the files to be stored in.
     * @return a preconfigured <code>StorageManager</code>.
     */
    public static StorageManager createStorageManager(String... path) {
        String actualPath = path.length == 0
                ? DEFAULT_FILEPATH
                : path[0];
        File dir = new File(actualPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        assert dir.exists() : "Directory is not created";
        return new StorageManager(actualPath);
    }

    /**
     * Loads all storage files into their respective <code>Storage</code> classes.
     *
     * @param taskList the <code>taskList</code> to be loaded into.
     * @param expenseList the <code>expenseList</code> to be loaded into.
     * @return a <code>String</code> containing the status of loading.
     */
    public String loadStores(TaskList taskList, ExpenseList expenseList) {
        String taskResult = taskStorage.loadList(taskList);
        String expenseResult = expenseStorage.loadList(expenseList);
        return taskResult + "\n" + expenseResult;
    }

    /**
     * Saves the <code>DukeLists</code> into the text files.
     *
     * @param taskList the <code>TaskList</code> to be saved.
     * @param expenseList the <code>ExpenseList</code> to be saved.
     * @throws DukeOperationException if the files cannot be saved.
     */
    public void saveStores(
            TaskList taskList, ExpenseList expenseList) throws DukeOperationException {
        taskStorage.saveToDisk(taskList);
        expenseStorage.saveToDisk(expenseList);
    }
}
