package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    protected String input;

    public ListCommand() {
    }

    /**
     * Executes listing of task in TaskList and shows error information.
     *
     * @param storage Storage data in hard disk.
     * @param taskList TaskList where task list is printed.
     * @param ui Ui that shows error message from the action.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.printList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
