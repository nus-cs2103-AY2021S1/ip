package duke.core.command;

import duke.core.DataStore;
import duke.core.storage.Storage;
import duke.designpattern.command.Executable;

import java.io.FileNotFoundException;

/**
 * Load taskList from file
 * The file should be in a defined csv format
 * Any existing task in taskList will be deleted
 */
public class LoadCommand implements Executable {

    private final DataStore dataStore;
    private final String filePath;

    /**
     * Load dataStore from a csv file as specified by filePath.
     * dataStore must be initialized and all entries will be cleared
     * on execution of this command
     * @param dataStore to load data into (Existing data will be deleted)
     * @param filePath A csv file to read data from
     */
    public LoadCommand(DataStore dataStore, String filePath) {
        this.dataStore = dataStore;
        this.filePath = filePath;
    }

    /**
     * Load taskList from file.
     * The file should be in a defined csv format.
     * All existing Tasks will be cleared and replaced with entries from the input csv file.
     * All existing History will be cleared.
     */
    @Override
    public void execute() {
        try {
            Storage.load(dataStore.getTaskList(), filePath);
            System.out.println("Load: " + dataStore.getTaskList().size() + " entries");
            // Clear history
            dataStore.getHistory().clear();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }

}
