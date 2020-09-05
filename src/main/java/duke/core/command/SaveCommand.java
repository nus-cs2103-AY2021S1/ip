package duke.core.command;

import duke.designpattern.command.Executable;
import duke.core.storage.Storage;
import duke.core.task.Task;

import java.io.IOException;
import java.util.List;

/**
 * Save taskList to file.
 * The tasks will be saved in a defined csv format.
 * Any existing data in filePath will be overwritten
 */
public class SaveCommand implements Executable {

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
            Storage.save(taskList, filePath);
            System.out.println("Save: " + taskList.size() + " entries");
        } catch (IOException e) {
            System.err.println("Error: Ensure directory exists and file is not in use");
        }
    }

}
