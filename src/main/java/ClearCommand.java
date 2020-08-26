import java.util.List;

public class ClearCommand extends Command {
    protected static final List<String> NAMES = List.of("clear", "clr");
    protected static final String DESCRIPTION = "Clear all tasks";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) throws AliceStorageException {
        String confirmation = ui.getConfirmation("Are you sure you want to clear all tasks? [Y/N]");
        ui.displayLine();

        if (confirmation.trim().toLowerCase().startsWith("y")) {
            tasks.clearAllTasks();
            storage.save(tasks.encode());
            ui.displayOutput("All tasks successfully cleared!");
        } else {
            // abort
            ui.displayOutput("Clear command aborted!");
        }
    }
}
