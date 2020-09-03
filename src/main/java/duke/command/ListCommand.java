package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

/**
 * Represents a Command where the Tasks in the TaskList are printed out sequentially during the usage
 * of the Duke programme.
 */
public class ListCommand extends Command {

    /**
     * Prints out all the Tasks in a given TaskList in order.
     * @param tasks the TaskList with Tasks to be printed out
     * @param storage unused Storage object
     * @param ui unused Ui object
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Here's yer current list of thingymajigs\n");
        buffer.append(tasks.list());
        return buffer.toString();
    }
}
