package duke.command;

import duke.Storage;
import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.task.Todo;
import duke.Ui;

/**
 * Command to create a Todo Task. Created by using "todo description"
 */
public class TodoCommand extends Command {

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Create a Todo with the user entered description, store it in TaskList,
     * print feedback to user and store the new Todo in Storage.
     *
     * @param tasks task list containing all tasks
     * @param ui ui for interaction with user
     * @param storage storage to retrieve and store tasks entered by user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.printMessage(String.format("Okay! I've added the following task: \n %s", todo.toString()));
        storage.updateTasks(tasks.getListOfTasks());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof TodoCommand) {
            return this.description.equals(((TodoCommand) other).description);
        } else {
            return false;
        }
    }
}
