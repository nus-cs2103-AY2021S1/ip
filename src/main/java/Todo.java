public class Todo extends Task {
    Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        return String.format("[T][%s] %s", mark, content);
    }
}