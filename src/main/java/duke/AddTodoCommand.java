package duke;

import java.time.LocalDate;

/**
 * Handles addition of todo-based Tasks.
 */

public class AddTodoCommand extends Command {
    /** duke.Command details in the form [TYPE, INFORMATION] */
    private final String[] instructions;

    /**
     * Constructor for duke.AddTodoCommand.
     * @param instructions Contains description.
     */
    public AddTodoCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the AddTodo duke.Command, adding a new duke.Task of type duke.Todo with description.
     * @param tasks duke.TaskList to be added to.
     * @param ui For user interaction.
     * @param storage To store the added task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task todoTask;

        if (instructions[1].contains(" /tags ")) {
            String[] tags;
            String[] nameAndTags = instructions[1].split(" /tags " );
            String todoName = nameAndTags[0];
            tags = nameAndTags[1].split(",");
            // cleanup whitespace
            for (int i = 0; i < tags.length; i++) {
                tags[i] = tags[i].strip();
            }

            todoTask = new Todo(todoName, tags);
        } else {
            todoTask = new Todo(instructions[1]);
        }

        return tasks.addTask(todoTask) + "\n" + storage.save(tasks);
    }
}
