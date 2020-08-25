package task;

public class Deadline extends Task {
    public Deadline(boolean completed, String content, String time) {
        super(completed, content, time);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        String time = getTime();
        return String.format("[D][%s] %s (by: %s)", mark, content, time);
    }
}