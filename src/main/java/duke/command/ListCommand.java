package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Here's yer current list of thingymajigs");
        tasks.list();
    }
}
