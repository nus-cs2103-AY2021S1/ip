public class DoneCommand extends Command {

    int taskDone;

    public DoneCommand(int taskDone) {
        this.taskDone = taskDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.completeTask(taskDone);
        storage.writeData(tasks);
        ui.printMessage(task.toString());
    }
}
