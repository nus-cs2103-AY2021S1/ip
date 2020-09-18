import java.util.List;

/**
 * Represents a ListCommand command which is a subclass of Command.
 */
public class ListCommand extends Command {
    TaskList taskManager;

    /**
     * Creates a DoneCommand object.
     * Lists all the current tasks stored in system and data file.
     *
     * @param taskManager reference to the same TaskList that manages list of Tasks.
     */
    public ListCommand(TaskList taskManager) {
        super("Here are the task(s) in your list:\n");
        this.taskManager = taskManager;
    }

    /**
     * Returns the list of tasks user has previously input message.
     *
     * @return a String of tasks user has previously input message as Duke response.
     */
    public String getOutput() {
        List<Task> tasks = taskManager.getTasks();
        int size = taskManager.getTaskNum();
        String parsedTasks = "";

        int index = 1;
        for (int i = 0; i < size; i++) {
            parsedTasks = parsedTasks + "  " + index + "." + tasks.get(i) + "\n";
            index++;
        }

        this.appendDukeMessage(parsedTasks);
        return this.toString();
    }

}
