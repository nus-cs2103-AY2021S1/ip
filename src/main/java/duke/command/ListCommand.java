package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    protected String input;

    public ListCommand() {
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.printList();
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
