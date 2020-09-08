package duke.tasks;

import java.io.IOException;

/**
 * Represents a todo command. This command handles the todo input
 * from users.
 */
public class TodoCommand extends Command {
    private String todoTask;

    /**
     * Constructor that stores the description string.
     * @param todo input from user.
     */
    public TodoCommand(String todo) {
        this.todoTask = todo;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        try {
            //add todo task to list of tasks
            tasks.todo(this.todoTask);
        } catch (DukeException e){
            return Todo.invalidInput();
        }

        //write task to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);

        return tasks.todoString(this.todoTask);
    }
}
