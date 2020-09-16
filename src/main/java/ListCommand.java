/**
 * Represents a class that returns a list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Lists out the tasks in the task list.
     * @param input user input.
     * @param taskManager task manager that contains a list of tasks.
     * @param fileHandler saves the input into a file.
     * @return String of the list of tasks.
     */
    @Override
    public String handle(String input, TaskManager taskManager, Storage fileHandler) {
        return taskManager.toString();
    }
}
