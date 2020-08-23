import exceptions.NoSuchTaskException;

public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        tasks.completeTask(taskNumber);
        storage.updateTasks(tasks.getListOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
