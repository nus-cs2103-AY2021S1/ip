package duke.task;

/**
 * Represents a task to be done without a specified date.
 */
public class ToDo extends Task {
    /**
     * Creates a new ToDo with the given description.
     *
     * @param description is the description of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gives a Todo task from the full task description in the file.
     *
     * @param loadTask is the full task description in the file.
     * @return a Todo task
     */
    public static ToDo load(String loadTask) {
        String[] splitTask = loadTask.split(" \\| ", 3);
        ToDo todo = new ToDo(splitTask[2]);
        if (splitTask[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Gives a string indicating whether the todo is done and the description of the todo.
     *
     * @param isFinished is the value 1 if todo is done or 0 if todo is not done.
     * @return a string indicating 1 or 0 and the description of the todo.
     */
    @Override
    public String save(int isFinished) {
        return "T | " + super.save(isFinished);
    }

    /**
     * Checks if this todo is equal to another obj.
     *
     * @param obj is the obj to check.
     * @return true if this is equals to the other obj.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo todo = (ToDo) obj;
            return this.description.equals(todo.description);
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return a string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
