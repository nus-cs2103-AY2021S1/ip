package commands;

import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.data.task.ToDo;
import duke.storage.Storage;

public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a simple task with only a description.\n"
            + "Example: " + COMMAND_WORD + " code Facebook";
    private final Task toDo;

    /**
     *
     * @param description
     */
    public ToDoCommand(String description) {
        this.description = description;
        toDo = new ToDo(description);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.addTask(toDo);
        storage.save(toDo);
        return new CommandResult("Adding " + toDo);
    }
}
