package duke.command;

import duke.storage.CsvToTask;
import duke.storage.Storage;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Load taskList from file
 * The file should be in a defined csv format
 */
public class LoadCommand implements Command {

    private final List<Task> taskList;
    private final String filePath;

    public LoadCommand(List<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    /**
     * Load taskList from file
     * The file should be in a defined csv format
     */
    @Override
    public void execute() {
        Storage.load(taskList, filePath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoadCommand)) return false;
        LoadCommand that = (LoadCommand) o;
        return taskList.equals(that.taskList) &&
                filePath.equals(that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, filePath);
    }
}
