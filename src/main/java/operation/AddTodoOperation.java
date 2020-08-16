package operation;

import task.TaskStorage;
import task.Todo;

public class AddTodoOperation extends AddOperation {
    public AddTodoOperation(String description, TaskStorage taskStorage) {
        super(description, taskStorage);
    }

    @Override
    public Todo createTask() {
        return Todo.createTodo(this.description);
    }
}
