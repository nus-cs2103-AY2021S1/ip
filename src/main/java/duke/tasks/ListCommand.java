package duke.tasks;

/**
 * Represents a list command. This command handles the list input
 * from users.
 */
public class ListCommand extends Command {
    private String listTask;

    /**
     * Constructor that stores the list string.
     * @param str input from user.
     */
    public ListCommand(String str) {
        this.listTask = str;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        //gets the list of tasks
        return tasks.list();
    }
}
