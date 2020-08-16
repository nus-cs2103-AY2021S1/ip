public class ToDo extends Task {

    public static ToDo createNewToDo(String argument) throws DukeException {
        if (argument.isBlank()) {
            throw new DukeException("Task name cannot be empty!");
        }

        return new ToDo(argument);
    }

    private ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
