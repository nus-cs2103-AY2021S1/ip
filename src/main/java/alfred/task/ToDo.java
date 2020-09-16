package alfred.task;

import alfred.AlfredException;

/**
 * Encapsulates data and methods specific to ToDo tasks.
 */
public class ToDo extends Task {

    private ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Creates a new instance of the class if the input is valid.
     *
     * @param argument Argument keyed in by the user when creating the task.
     * @return New instance of the ToDo class.
     * @throws AlfredException If task name is empty.
     */
    public static ToDo createNewToDo(String argument) throws AlfredException {

        assert argument != null : "Task argument cannot be null";

        if (argument.isBlank()) {
            throw new AlfredException("Task name cannot be empty!");
        }

        return new ToDo(argument);
    }

    /**
     * Generates a single line string that will be saved in storage.
     *
     * @return String to be saved in storage.
     */
    @Override
    public String generateStorageString() {
        return String.format("TODO | %s | %s", isDone ? "TRUE" : "FALSE", taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
