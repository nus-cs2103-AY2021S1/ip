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
        try {
            Storage.load(taskList, filePath);
            System.out.println("Load: " + taskList.size() + " entries");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }

}
