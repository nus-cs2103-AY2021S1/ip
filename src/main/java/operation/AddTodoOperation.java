package operation;

import exception.DukeException;
import task.TaskStorage;
import task.Todo;

public class AddTodoOperation extends AddOperation {
    public AddTodoOperation(String[] commands, TaskStorage taskStorage) {
        super(commands, taskStorage);
    }

    @Override
    public Todo createTask() throws DukeException {
        return Todo.createTodo(this.commands);
    }
}
