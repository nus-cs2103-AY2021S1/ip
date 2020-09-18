import java.util.ArrayList;
import java.util.List;

/**
 * Represents a FindCommand command which is a subclass of Command.
 */
public class FindCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    /**
     * Creates a FindCommand object.
     * Finds a task based on keyword/String input from user.
     *
     * @param userInput represents the user String input.
     * @param taskManager reference to the same TaskList that manages list of Tasks.
     */
    public FindCommand(String userInput, TaskList taskManager) {
        super("");
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

    /**
     * Returns the list of matching tasks message.
     *
     * @return a String of matching tasks message as Duke response.
     */
    public String getOutput() {
        int length = userInput.length();
        if (length == 5) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.NO_KEYWORD);
            this.appendDukeMessage(exceptionCommand.toString());

        } else {
            String keyword = userInput.substring(5, length);
            List<Task> allTasks = taskManager.getTasks();
            int fullSize = taskManager.getTaskNum();
            List<Task> filteredTasks = new ArrayList<>();

            for (int i = 0; i < fullSize; i++) {
                Task task = allTasks.get(i);
                String taskString = task.toString();
                if (taskString.contains(keyword)) {
                    filteredTasks.add(task);
                }
            }

            String output = "Here are the matching task(s) in your list:\n";
            int partialSize = filteredTasks.size();
            int index = 1;
            for (int i = 0; i < partialSize; i++) {
                output = output + "  " + index + "." + filteredTasks.get(i) + "\n";
                index++;
            }

            this.appendDukeMessage(output);
        }

        return this.toString();
    }
}
