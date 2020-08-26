public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toFileFormat() {
        return "T" + " | " + super.toFileFormat() + "\n";
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
