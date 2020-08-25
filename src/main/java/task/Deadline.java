package task;

import java.time.LocalDate;

public class Deadline extends Task {
    public Deadline(boolean completed, String content, LocalDate time) {
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
        LocalDate time = getTime();
        return String.format("[D][%s] %s (by: %s)", mark, content, time);
    }
}