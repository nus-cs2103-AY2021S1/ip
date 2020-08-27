package duke.operation;

import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents operation that adds <code>Todos</code>.
 */
public class AddTodoOperation extends AddOperation {

    /**
     * Constructor method.
     * @param description the description of the <code>Todo</code>.
     * @param taskList the <code>TaskList</code> that <code>Todo</code> is to be added into.
     */
    public AddTodoOperation(String description, TaskList taskList) {
        super(description, taskList);
    }

    /**
     * Creates the associated <code>Todo</code>.
     * @return an uncompleted <code>Todo</code>.
     */
    @Override
    public Todo createTask() {
        return Todo.createTodo(this.description);
    }
}
