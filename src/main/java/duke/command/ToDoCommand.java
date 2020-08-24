package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * ToDo command that adds a todo task
 */
public class ToDoCommand extends CreateCommand {
    public ToDoCommand(String commandString) {
        super(CommandType.TODO, commandString);
    }

    /**
     * Adds a todo task to the task list
     *
     * @param tasks task list of tasks
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        String description = this.getTaskDescription();
        this.addTask(tasks, new ToDo(description));
    }
}
