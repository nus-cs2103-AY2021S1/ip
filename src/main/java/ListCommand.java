package main.java;

import java.util.Comparator;

/**
 * ListCommands list out the current TaskList
 *
 * @author Lio
 */
public class ListCommand extends Command {
    private Comparator<Task> comparator;

    /**
     * Empty Constructor
     */
    public ListCommand() {
        this.comparator = (a, b) -> 0;
    }

    /**
     * Constructor
     *
     * @param comparator comparator used to sort the tasks before listing
     */
    public ListCommand(Comparator<Task> comparator) {
        this.comparator = comparator;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks.sort(comparator));
    }
}
