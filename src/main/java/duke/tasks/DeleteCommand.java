package duke.tasks;

import java.io.IOException;

/**
 * Represents a Delete Command. This command handles the delete input
 * from users.
 */
public class DeleteCommand extends Command {
    protected String delete;

    /**
     * Constructor that stores the string to be deleted.
     * @param delete string to be removed from list of tasks.
     */
    public DeleteCommand(String delete) {
        this.delete = delete;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //add event task to list of tasks
        tasks.delete(this.delete);

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);
    }
}
