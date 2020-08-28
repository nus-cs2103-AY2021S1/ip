package duke.command;

import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.List;

/**
 * Save taskList to file
 * The tasks will be saved in a defined csv format
 */
public class SaveCommand implements Command {

    private final List<Task> taskList;
    private final String filePath;

    public SaveCommand(List<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
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
            System.out.println("Error: Could not save to file. Ensure directory exists and file is not in use");
        }
    }

}
