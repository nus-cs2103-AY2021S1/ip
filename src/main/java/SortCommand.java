import java.util.ArrayList;

public class SortCommand extends Command {

    private String userCommand;

    public SortCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    @Override
    String execute(TaskList tasks, UI ui) {
            ArrayList<Task> sortedTasks = tasks.sortTask();
            String dukeResponse = ui.sortTasks(sortedTasks);
            return dukeResponse;

    }
}
