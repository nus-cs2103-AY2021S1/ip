package task;

public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    public String toDataFileFormat(){
        return String.format("T | %d | %s", isDone ? 1 : 0, content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
