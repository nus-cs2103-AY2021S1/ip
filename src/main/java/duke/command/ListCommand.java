package duke.command;

import duke.*;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printList(tasks); // don't have to save tasks when listing
    }
}