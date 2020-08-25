package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public final static String COMMAND = "list";
    
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}
