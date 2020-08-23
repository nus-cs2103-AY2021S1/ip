/**
 * Represents a done/ticked command.
 */
public class DoneCommand extends Command {

    private String afterCommand;

    /**
     * Constructor for the done command.
     * @param afterCommand task id to be ticked off
     */
    public DoneCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs a valid number
        int taskNo = Integer.parseInt(afterCommand) - 1;
        if (taskNo >= taskList.tasksSize() || taskNo < 0) {
            ui.throwDukeException(new DukeException("Please enter a valid task no!"));
        } else {
            taskList.markTaskCompleted(taskNo);
            ui.displayCompletedTask(taskList.get(taskNo));
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
