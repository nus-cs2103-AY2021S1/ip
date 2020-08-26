import java.util.List;

public class DoneCommand extends Command {
    protected static final List<String> NAMES = List.of("done");
    protected static final String DESCRIPTION = "Mark a task as done";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <task number>";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) throws AliceStorageException {
        try {
            Task completedTask = tasks.markTaskAsDone(taskNumber);
            ui.displayOutput("Great work! I've marked this task as done:\n    " + completedTask);
            storage.save(tasks.encode());
        } catch (InvalidCommandException ex) {
            ui.displayWarning("Failed to mark task as done. " + ex.getMessage());
        }
    }
}
