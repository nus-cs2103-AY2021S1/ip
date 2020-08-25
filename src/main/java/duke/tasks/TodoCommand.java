package duke.tasks;

import java.io.IOException;

/**
 * Represents a todo command. This command handles the todo input
 * from users.
 */
public class TodoCommand extends Command{
    protected String todo;

    /**
     * Constructor that stores the description string.
     * @param todo input from user.
     */
    public TodoCommand(String todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //add todo task to list of tasks
        tasks.todo(this.todo);

        //write task to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);
    }
}
