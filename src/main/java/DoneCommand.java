public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printDoneMessage(taskList.markTaskAsDone(taskNumber));
        storage.updateTasks(taskList);
    }

    @Override
    public boolean isInProgram() {
        return true;
    }
}
