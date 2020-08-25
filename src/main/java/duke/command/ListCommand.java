package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that lists out the taskList
 */
public class ListCommand extends Command {

    /**
     * Creates a list command
     */
    public ListCommand(){}

    /**
     * Prints out the tasks inside TaskList.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTasks());
    }
}
