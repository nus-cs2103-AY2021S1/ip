package duke.tasks;

import java.io.IOException;

/**
 * Represents a Find Command. This command handles the find input
 * from users.
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Constructor that stores the find command string.
     * @param toFind input from user.
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //find tasks with matching keywords in the string
        return tasks.find(this.toFind);
    }
}
