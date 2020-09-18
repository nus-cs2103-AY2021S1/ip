package duke.commands;

import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.data.task.ToDo;
import duke.storage.Storage;

/**
 * Responsible for the logic of adding a ToDo into the task list.
 */
public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a simple task with only a description.\n"
            + "Example: " + COMMAND_WORD + " code Facebook\n";
    private final Task toDo;

    /**
     * Constructor initialising the description.
     * @param description Name of the ToDo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
        toDo = new ToDo(description);
    }

    /**
     * Adds the ToDo task into the task list and apppends it
     * to the data file.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult noting the ToDo task has been added.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.addTask(toDo);
        storage.save(toDo);
        return new CommandResult("Adding " + toDo);
    }
}
