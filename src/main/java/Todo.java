public class Todo extends Task {

    public Todo(String content, Boolean isComplete) throws DukeException {
        super(content, isComplete);
    }
    public Todo(String content) throws DukeException {
        super(content);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveString() {
        return String.format("T/%s/ ", super.toSaveString());
    }
}
