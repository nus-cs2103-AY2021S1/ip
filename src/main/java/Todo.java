public class Todo extends Task  {
    Todo(String name) {
        super(name);
    }

    Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
