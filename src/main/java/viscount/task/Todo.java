package viscount.task;

import java.time.LocalDateTime;

/**
 * Represents a todo, a type of task.
 */
public class Todo extends Task {
    private static final String STRING_FORMAT = "[T][%s] %s";
    private static final String TASK_DATA_FORMAT = "%s|%d|%s";

    /**
     * Instantiates a new todo task.
     *
     * @param description Description of todo added.
     * @param isDone Represents if todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(TaskType.TODO, description, isDone);
    }

    /**
     * Returns whether the task has a date time field.
     *
     * @return False as todos do not have a date time field.
     */
    @Override
    public boolean hasDateTime() {
        return false;
    }

    /**
     * Gets the event time of the event.
     *
     * @return Event time of the event.
     * @throws UnsupportedOperationException Always as a todo does not have a date time field.
     */
    @Override
    public LocalDateTime getDateTime() {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the date time of the task.
     *
     * @param newDateTime New date time of the task.
     * @throws UnsupportedOperationException Always as a todo does not have a date time field.
     */
    @Override
    public void setDateTime(LocalDateTime newDateTime) {
        throw new UnsupportedOperationException();
    }

    /**
     * Gives a task data representation of the task in String format.
     *
     * @return Task data representation of the task.
     */
    @Override
    public String toTaskData() {
        return String.format(Todo.TASK_DATA_FORMAT, taskType.name(), isDone ? 1 : 0, description);
    }

    /**
     * Gives a displayable string representation of the task.
     *
     * @return Displayable string representation of the task.
     */
    @Override
    public String toString() {
        return String.format(Todo.STRING_FORMAT, getStatusIcon(), description);
    }

    /**
     * Compares this task with another object for equality.
     *
     * @param o Object compared.
     * @return True if this task is equal to the object, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Todo todo = (Todo) o;
        boolean hasSameDescription = this.description.equals(todo.description);
        boolean hasSameDone = this.isDone == todo.isDone;
        return hasSameDescription && hasSameDone;
    }
}
