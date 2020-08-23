import exceptions.NoSuchTaskException;

public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        Task completedTask = tasks.completeTask(taskNumber);
        ui.printMessage(String.format("Great! I've marked the following task as done: \n %s", completedTask));
        storage.updateTasks(tasks.getListOfTasks());
    }
}
