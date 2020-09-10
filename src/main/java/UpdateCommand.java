public class UpdateCommand extends Command {
    private int taskNum;
    private String taskName;
    private String taskDate;

    public UpdateCommand(int taskNum, String taskName, String taskDate) {
        this.taskNum = taskNum;
        this.taskName = taskName;
        this.taskDate = taskDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null && ui != null && storage != null
                : "TaskList, Ui or Storage is not supposed to be null";

        if (tasks.getSize() == 0) {
            throw new EmptyListException();
        }

        if (this.taskNum == 0 || this.taskNum > tasks.getSize()) {
            throw new InvalidTaskNumberException();
        }

        Task t = tasks.getTask(this.taskNum);
        updateTask(t);
        storage.updateData(tasks);
        return ui.showUpdate(t);
    }

    public void updateTask(Task t) throws DukeException {
        if (this.taskName != null) {
            t.setTaskName(this.taskName);
        } else {
            t.setTaskDateTime(this.taskDate);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
