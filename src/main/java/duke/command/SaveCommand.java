package duke.command;

import duke.storage.Storage;
import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
        Storage.save(taskList, filePath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveCommand)) return false;
        SaveCommand that = (SaveCommand) o;
        return taskList.equals(that.taskList) &&
                filePath.equals(that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, filePath);
    }
}
