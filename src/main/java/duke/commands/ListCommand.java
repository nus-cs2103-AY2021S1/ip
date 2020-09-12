package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Encapsulates a command for Mrs Dino to list all current tasks.
 */
public class ListCommand extends Command {

    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = false;

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
