package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.storage.Storage;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final String commandSuffix;

    public TodoCommand(String commandSuffix) {
        this.commandSuffix = commandSuffix;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(commandSuffix);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
