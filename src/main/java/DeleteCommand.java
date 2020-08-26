public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.retrieve(taskNumber);
            tasks.remove(taskNumber);
            ui.deleteTaskMessage(t, tasks);
        } catch (Exception e) {
            throw new DukeException("Task does not exist/invalid task number.");
        }
    }
}
