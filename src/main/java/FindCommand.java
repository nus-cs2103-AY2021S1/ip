import java.util.ArrayList;
import java.util.Iterator;

public class FindCommand extends Command {
    FindCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.getMatchingTasks(fullCommand.substring(5));
        System.out.println(matchingTasksMessage(matchingTasks));
    }

    public String matchingTasksMessage(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();

        if (matchingTasks.size() == 0) {
            sb.append("there are no tasks matching the given search");
        } else {
            sb.append("here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(i+1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        return sb.toString().trim();
    }
}
