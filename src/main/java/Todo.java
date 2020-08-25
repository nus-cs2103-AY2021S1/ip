/**
 * Todo is the most general type of Task.
 */
class Todo extends Task {
    Todo(String name) {
        super(name, Type.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
