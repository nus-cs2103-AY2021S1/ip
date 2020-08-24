public class DoneCommand extends Command {

    int taskNumber;
    protected DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.markTaskDoneInList(taskNumber);
            ui.printMarkedTask(task);
            storage.writeToFile(tasks.getTasks());
        } catch (DukeException e) {
            throw e;
        }
    }
}
