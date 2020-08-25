package duke.tasks;

/**
 * Represents a list command. This command handles the list input
 * from users.
 */
public class ListCommand extends Command{
    protected String list;

    /**
     * Constructor that stores the list string.
     * @param str input from user.
     */
    public ListCommand(String str) {
        this.list = str;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        //gets the list of tasks
        tasks.list();
    }
}
