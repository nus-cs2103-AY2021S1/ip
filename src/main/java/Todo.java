public class Todo extends Task {

    public Todo(String name) throws DukeEmptyDescException {
        super(name, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}