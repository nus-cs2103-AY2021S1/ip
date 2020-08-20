public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String symbol = this.done ? "\u2713" : "\u2717";
        return String.format("[T][%s] %s", symbol, this.name);
    }
}
