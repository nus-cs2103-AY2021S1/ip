public abstract class EditCommand extends Command {
    protected int taskNumber;

    EditCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
}
