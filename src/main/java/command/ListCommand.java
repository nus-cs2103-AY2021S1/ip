package command;

import java.util.concurrent.atomic.AtomicInteger;

import storage.Storage;
import task.TaskList;
import ui.Ui;




/**
 * ListCommand would execute the program when user specify
 * "list" as the command. This would show all the existing
 * list of task to the user.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand without any
     * argument passed.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes parsed user command. The result is:
     * 1. Shows all the existing tasks in the list via Ui object.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();

        AtomicInteger index = new AtomicInteger(1);
        tasks.getTasks().stream().forEach(t -> sb.append(index.getAndIncrement()).append(". ").append(t).append("\n"));


        return ui.getMessageTemplate("Here are the tasks in your list:\n" + sb.toString());
    }
}
