import java.util.List;

public class ListCommand extends Command {
    protected static final List<String> NAMES = List.of("list", "ls");
    protected static final String DESCRIPTION = "Lists all tasks";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.getAllTasks();
        if (output == null) {
            ui.displayOutput("You have no tasks at the moment.");
        } else {
            ui.displayOutput("Here are the tasks in your list:\n" + output);
        }
    }
}
