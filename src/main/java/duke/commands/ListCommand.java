package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    private final boolean HAS_FINISHED = false;
    public ListCommand() {

    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        // for CLI
        taskList.list();
        // for GUI
        ArrayList<Task> todoList = taskList.getList();
        String messageAfterExecution = taskListToString(todoList);
        return new CommandResult(messageAfterExecution);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
