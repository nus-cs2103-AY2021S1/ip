public class Todo extends Task {
    public Todo(String content) throws DukeException {
        super(content);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
