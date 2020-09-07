package duke.operation.addtaskoperation;

import duke.list.TaskList;
import duke.task.Todo;

/**
 * Represents operation that adds <code>Todos</code>.
 */
public class AddTodoTaskOperation extends AddTaskOperation {

    /**
     * Constructor method.
     *
     * @param description the description of the <code>Todo</code>.
     * @param taskList the <code>TaskList</code> that <code>Todo</code> is to be added into.
     */
    public AddTodoTaskOperation(String description, TaskList taskList) {
        super(description, taskList);
    }

    /**
     * Creates the associated <code>Todo</code>.
     *
     * @return an uncompleted <code>Todo</code>.
     */
    @Override
    public Todo createTask() {
        return Todo.createTodo(description);
    }
}
