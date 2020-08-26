public class DoneCommand extends Command {

    private final int taskIndex;

    DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.doneTask(taskIndex);
        storage.updateTasks(taskList);
        ui.showDoneTask(task);
    }
}
