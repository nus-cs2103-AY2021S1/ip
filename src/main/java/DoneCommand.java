public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Task.totalTasks == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }

        if (this.taskNum == 0 || this.taskNum > Task.totalTasks) {
            throw new DukeException("You don't have a task with that number! ><\n" +
                    "Can you try a different number?");
        }

        Task t = tasks.getTask(this.taskNum);
        t.markDone();
        ui.showDone(t);
        storage.updateData(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
