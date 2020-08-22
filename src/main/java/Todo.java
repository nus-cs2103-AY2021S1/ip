public class Todo extends Task {

    Todo(String taskName) throws DukeException {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[TODO] " + super.toString();
    }
}
