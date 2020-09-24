package duke.tasks;

/**
 * Represents a list command. This command handles the list input
 * from the user.
 */
public class ListCommand extends Command {
    private String listTask;

    /**
     * Constructor that stores the list string.
     */
    public ListCommand() { }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        //gets the list of tasks
        return tasks.list();
    }
}
