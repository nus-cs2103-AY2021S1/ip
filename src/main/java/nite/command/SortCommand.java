package nite.command;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import nite.storage.Storage;
import nite.task.Task;
import nite.task.TaskList;
import nite.ui.Ui;

/**
 * Represents a SortCommand where user wants to sort the list of tasks.
 */
public class SortCommand extends Command {

    private final String[] sortingParameters;

    /**
     * Creates a SortCommand.
     *
     * @param sortingParameters Order and Type of tasks to sort.
     */
    public SortCommand(String ... sortingParameters) {
        this.sortingParameters = sortingParameters;
    }

    /**
     * Executes the command to sort and display the tasklist.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @return Sorted tasks;
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> sortedTasks = new ArrayList<>();
        for (int i = 1; i < sortingParameters.length; i++) {
            sortedTasks.addAll(tasks.sortedTasks(sortingParameters[i]));
        }
        AtomicInteger counter = new AtomicInteger(1);
        Stream<String> stringsStream = sortedTasks.stream().map(t -> String.format("%n    %d.%s",
                counter.getAndIncrement(), t));
        String tasksString = stringsStream.reduce("", (s, t) -> s += String.format("%s", t));
        return ui.showAction("  Here are the sorted tasks:" + tasksString);
    }
}
