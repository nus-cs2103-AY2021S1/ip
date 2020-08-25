package task;

public class Todo extends Task {
    public Todo(boolean completed, String content) {
        super(completed, content, null);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        return String.format("[T][%s] %s", mark, content);
    }
}