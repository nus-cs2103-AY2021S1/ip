/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    private String afterCommand;

    /**
     * Constructor for the delete command.
     * @param afterCommand task id to be deleted
     */
    public DeleteCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs a valid number
        int taskNo = Integer.parseInt(afterCommand) - 1;
        if (taskNo >= taskList.tasksSize() || taskNo < 0) {
            return ui.throwDukeException(new DukeException("Please enter a valid task no!"));
        } else {
            Task deletedTask = taskList.get(taskNo);
            taskList.delete(taskNo);
            return ui.displayDeletedTask(deletedTask, taskList.tasksSize());
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
