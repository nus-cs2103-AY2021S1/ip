import exceptions.DukeException;
import exceptions.NoSuchTaskException;

public class DeleteCommand extends Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        Task deletedTask = tasks.deleteTask(taskNumber);
        ui.printMessage(String.format("Okay, I've deleted the following task: \n %s", deletedTask.toString()));
        storage.updateTasks(tasks.getListOfTasks());
    }
}
