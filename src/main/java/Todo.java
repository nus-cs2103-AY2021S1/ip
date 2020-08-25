public class Todo extends Task {

    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[T][%s] %s", doneSymbol, getName());
    }

    @Override
    public String toSaveString() {
        return String.format("T|%d|%s", isDone() ? 1 : 0, getName());
    }
}
