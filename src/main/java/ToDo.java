public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getStorageFormat() {
        return "T | " + super.getStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static ToDo makeTaskFromInput(String taskName) throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badToDo();
        }
        return new ToDo(taskName);
    }
}
