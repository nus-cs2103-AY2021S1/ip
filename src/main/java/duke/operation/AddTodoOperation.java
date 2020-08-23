package duke.operation;

import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents operation that adds Todo objects.
 */
public class AddTodoOperation extends AddOperation {

    /**
     * Constructor method.
     * @param description the description of the Todo.
     * @param taskList the TaskList that Todo is to be added into.
     */
    public AddTodoOperation(String description, TaskList taskList) {
        super(description, taskList);
    }

    /**
     * Creates the associated Todo.
     * @return an uncompleted Todo.
     */
    @Override
    public Todo createTask() {
        return Todo.createTodo(this.description);
    }
}
