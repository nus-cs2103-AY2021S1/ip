public class DeleteCommand extends Command {

    // Attributes
    private final int taskNumber;

    // Constructor
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    // Methods
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showMessage(tasks.deleteTask(taskNumber));
    }

    @Override
    boolean isExit() {
        return false;
    }
}
