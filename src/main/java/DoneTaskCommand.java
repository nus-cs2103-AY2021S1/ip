public class DoneTaskCommand implements Command {
    private final int n;

    public DoneTaskCommand(int n) {
        this.n = n;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeError {
        if (n < 1 || n > taskList.numberOfTasks()) {
            throw new InvalidRangeError();
        }
        Task task = taskList.getTaskList().get(n - 1);
        if (task.getIsDone()) {
            throw new TaskAlreadyCompletedError(task);
        }
        ui.doneTask(task.getDescription());
        task.markAsDone();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
