package duke.command;

import duke.storage.Storage;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Load taskList from file
 * The file should be in a defined csv format
 * Any existing task in taskList will be deleted
 */
public class LoadCommand implements Command {

    private final List<Task> taskList;
    private final String filePath;

    /**
     * Load taskList from a csv file as specified by filePath.
     * taskList must be initialized and all entries will be cleared
     * on execution of this command
     * @param taskList
     * @param filePath
     */
    public LoadCommand(List<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    /**
     * Load taskList from file.
     * The file should be in a defined csv format.
     * All existing Tasks will be cleared and replaced with
     * entries from the input csv file
     */
    @Override
    public void execute() {
        try {
            Storage.load(taskList, filePath);
            System.out.println("Load: " + taskList.size() + " entries");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }

}
