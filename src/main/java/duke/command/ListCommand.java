package duke.command;

import duke.*;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}