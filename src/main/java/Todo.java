public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String write() {
        return String.format("T,%s", super.write());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
