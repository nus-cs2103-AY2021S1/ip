package duke.command;

import duke.main.TaskList;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Abstraction for an operation involving adding of todos to a list of task.
 */
public class AddToDoCommand extends AddTaskCommand {
    /**
     * Constructs a new AddToDoCommand object.
     *
     * @param details Description of task to create.
     * @param taskList TaskList to be operated on.
     */
    public AddToDoCommand(String details, TaskList taskList) {
        super(details, taskList);
    }

    /**
     * Converts the given String of details into an ToDo to be added to the TaskList.
     *
     * @return Newly created ToDo.
     */
    @Override
    protected Task getTask() {
        return ToDo.create(details);
    }
}
