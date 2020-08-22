package duke.operation;

import duke.task.TaskList;
import duke.task.Todo;

public class AddTodoOperation extends AddOperation {
    public AddTodoOperation(String description, TaskList taskList) {
        super(description, taskList);
    }

    @Override
    public Todo createTask() {
        return Todo.createTodo(this.description);
    }
}
