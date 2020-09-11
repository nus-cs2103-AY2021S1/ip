package duke.core.command;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import duke.core.storage.Storage;
import duke.core.task.Task;
import duke.designpattern.command.CommandException;
import duke.designpattern.command.Executable;

/**
 * Save taskList to file.
 * The tasks will be saved in a defined csv format.
 * Any existing data in filePath will be overwritten
 */
public class SaveCommand implements Executable {

    private static final Logger logger = Logger.getLogger(SaveCommand.class.getName());

    private final List<Task> taskList;
    private final String filePath;

    /**
     * Create a SaveCommand which saves all item in taskList into
     * a csv file specified by filePath.
     * Any existing data in filePath will be overwritten
     * @param taskList Containing Tasks to be saved
     * @param filePath The file to save taskList into (Existing data will be overwritten)
     */
    public SaveCommand(List<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
        assert this.taskList != null;
        assert this.filePath != null;
    }

    /**
     * Save taskList to file
     * The tasks will be saved in a defined csv format
     */
    @Override
    public void execute() {
        try {
            logger.info(SaveCommand.class.getSimpleName() + ": Saving data into '" + filePath + "'");
            Storage.save(taskList, filePath);
            System.out.println("Save: " + taskList.size() + " entries");
        } catch (IOException e) {
            logger.warning(SaveCommand.class.getSimpleName() + ": File/Folder not available");
            throw new CommandException("Error: Ensure directory exists and file is not in use");
        }
    }

}
