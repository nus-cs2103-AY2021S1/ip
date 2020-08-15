public class Todo extends Task {
    Todo(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
