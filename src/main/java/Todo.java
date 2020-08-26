public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String symbol = this.done ? "✓" : "✘";
        return String.format("[T]%s", super.toString());
    }
}
