package sparkles.command;

import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

public class TaskUnclearCommand extends Command {

    public TaskUnclearCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("     Task need to be more specific!");
    }

}
