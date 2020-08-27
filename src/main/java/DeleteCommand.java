public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Task.totalTasks == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }

        if (this.taskNum == 0 || this.taskNum > Task.totalTasks) {
            throw new DukeException("You don't have a task with that number! ><\n"
                    + "Can you try a different number?");
        }

        Task.totalTasks--;
        Task t = tasks.getTask(this.taskNum);
        tasks.deleteTask(this.taskNum);
        ui.showDelete(t);
        storage.updateData(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
