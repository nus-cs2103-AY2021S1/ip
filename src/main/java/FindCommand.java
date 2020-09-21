import java.util.Map;

public class FindCommand extends Command {

    // Attributes
    private final String substring;

    // Constructor
    public FindCommand(String substring) {
        this.substring = substring;
    }


    @Override
    String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage, Map<String, Runnable> runnables) {
        TaskList filteredTasks = tasks.findTasks(this.substring);
        return String.format("Here are your tasks matching %s\n"
                + "%s", this.substring, filteredTasks);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
