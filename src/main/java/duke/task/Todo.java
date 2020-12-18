package duke.task;

import java.time.LocalDate;

import duke.exception.InvalidTodoException;

public class Todo extends Task {
    private static final String TODO_ICON = "[T]";
    private static final String TODO_NAME = "todo ";

    private Todo(String desc) {
        super(desc);
    }

    /**
     * Factory method for creating a todo task.
     *
     * @param details String details of the task.
     * @return Todo the todo task.
     * @throws InvalidTodoException If the format of the details is invalid.
     */
    protected static Todo createTodo(String details) throws InvalidTodoException {
        if (details.equals("")) {
            throw new InvalidTodoException();
        }
        return new Todo(details);
    }

    @Override
    public String toSaveString() {
        String todoCommand = (isDone ? 1 : 0) + TODO_NAME + description;
        return todoCommand + " " + getTagsSaveString();
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return false;
    }

    @Override
    public boolean isDueInNDays(int n) {
        assert n >= 0 : "isDueWithinNDays should receive a non-negative input";
        return true;
    }

    @Override
    public String toString() {
        String tags = stringifyTags();
        String displayTags = tags.equals("")
                ? ""
                : " Tags: " + tags;
        return TODO_ICON + super.toString() + displayTags;
    }
}
