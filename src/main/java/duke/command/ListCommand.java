package duke.command;

import duke.*;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasksChatWindow(tasks.getTasks());
    }

}
