package task;

public class Deadline extends Task {
    private final String time;

    public Deadline(boolean completed, String content, String time) {
        super(completed, content);
        this.time = time;
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        return String.format("[D][%s] %s (by: %s)", mark, content, time);
    }
}