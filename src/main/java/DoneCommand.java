public class DoneCommand extends Command {

    // Attributes
    private final int taskNumber;

    // Constructor
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    // Methods
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showMessage(tasks.markAsDone(taskNumber));
    }

    @Override
    boolean isExit() {
        return false;
    }
}
