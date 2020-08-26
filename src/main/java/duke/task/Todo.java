package duke.task;

import duke.exception.InvalidTodoException;

import java.time.LocalDate;

public class Todo extends Task {
    private Todo(String desc) {
        super(desc);
    }

    /**
     * Factory method for creating a todo task.
     *
     * @param details String details of the task
     * @return Todo the todo task
     * @throws InvalidTodoException If the format of the details is invalid
     */
    protected static Todo createTodo(String details) throws InvalidTodoException {
        if (details.equals("")) {
            throw new InvalidTodoException();
        }
        return new Todo(details);
    }

    @Override
    public String toSaveString() {
        return (isDone ? 1 : 0) + "todo " + description;
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
