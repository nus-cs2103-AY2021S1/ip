public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public String format() {
        return "T" + SAVE_DELIMITER + super.format();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
