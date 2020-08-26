import java.util.List;

public class DeleteCommand extends Command {
    protected static final List<String> NAMES = List.of("delete");
    protected static final String DESCRIPTION = "Delete a task";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <task number>";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) throws AliceStorageException {
        try {
            Task deletedTask = tasks.deleteTask(taskNumber);
            ui.displayOutput("Roger. I've removed this task from your list:\n    "
                    + deletedTask
                    + "\nNow you have " + tasks.getNumberOfTasks() + " task in your list");
            storage.save(tasks.encode());
        } catch (InvalidCommandException ex) {
            ui.displayWarning("Failed to delete task. " + ex.getMessage());
        }
    }
}