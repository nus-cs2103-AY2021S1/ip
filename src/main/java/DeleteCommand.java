public class DeleteCommand extends Command {
    private String afterCommand;

    public DeleteCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs a valid number
        int taskNo = Integer.parseInt(afterCommand) - 1;
        if (taskNo >= taskList.tasksSize() || taskNo < 0) {
            ui.throwDukeException(new DukeException("Please enter a valid task no!"));
        } else {
            ui.displayDeletedTask(taskList.get(taskNo), taskList.tasksSize() - 1);
            taskList.delete(taskNo);
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
