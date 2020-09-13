package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command for Mrs Dino to add a Todo task.
 */
public class TodoCommand extends Command {
    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = false;

    /**
     * Details of the todo task.
     */
    private String todoTaskDetails;

    /**
     * Constructs a new TodoCommand.
     *
     * @param todoTaskDetails Details of the todo task.
     */
    public TodoCommand(String todoTaskDetails) {
        this.todoTaskDetails = todoTaskDetails;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTodo("0", todoTaskDetails);
        storage.saveTasks(taskList);
        int size = taskList.getSize();
        Task targetTask = taskList.get(size - 1);
        ui.printTaskAdded(targetTask.toString(), size);
        String messageAfterExecution = addTaskToString(targetTask, size);
        return new CommandResult(messageAfterExecution);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
