package duke.core.command;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import duke.core.DukeData;
import duke.core.storage.Storage;
import duke.designpattern.command.CommandException;
import duke.designpattern.command.Executable;

/**
 * Load taskList from file
 * The file should be in a defined csv format
 * Any existing task in taskList will be deleted
 */
public class LoadCommand implements Executable {

    private static final Logger logger = Logger.getLogger(LoadCommand.class.getName());

    private final DukeData dukeData;
    private final String filePath;

    /**
     * Load dataStore from a csv file as specified by filePath.
     * dataStore must be initialized and all entries will be cleared
     * on execution of this command
     * @param dukeData to load data into (Existing data will be deleted)
     * @param filePath A csv file to read data from
     */
    public LoadCommand(DukeData dukeData, String filePath) {
        this.dukeData = dukeData;
        this.filePath = filePath;
        assert this.dukeData != null;
        assert this.filePath != null;
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
            logger.info(LoadCommand.class.getSimpleName() + ": Loading data from '" + filePath + "'");
            Storage.load(dukeData.getTaskList(), filePath);
            System.out.println("Load: " + dukeData.getTaskList().size() + " entries");
            // Clear history
            logger.warning(LoadCommand.class.getSimpleName() + ": Clearing command history");
            dukeData.getHistory().clear();
        } catch (FileNotFoundException e) {
            logger.warning(LoadCommand.class.getSimpleName() + ": File Not Found");
            throw new CommandException("Error: File not found");
        }
    }

}
