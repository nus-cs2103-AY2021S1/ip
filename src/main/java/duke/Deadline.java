package duke;

import java.time.LocalDate;
import java.time.LocalTime;

class Deadline extends Task {
    private static final String SYMBOL = "[D]";

    private LocalDate deadlineDay;
    private LocalTime deadlineTime;

    public Deadline(String name, String datetime) {
        super(name, TaskType.Deadline);
        String[] dateTimeArr = datetime.split(" "); // Assuming must have date and time
        String date = dateTimeArr[0];
        String time = dateTimeArr[1];
        this.deadlineDay = createDeadlineDay(date);
        this.deadlineTime = createDeadlineTime(time);
    }

    public LocalDate getDeadlineDay() {
        return deadlineDay;
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    public static LocalDate createDeadlineDay(String date) {
        return LocalDate.parse(date);
    }

    public static LocalTime createDeadlineTime(String time) {
        return LocalTime.parse(time);
    }

    @Override
    public String getFileString() {
        String status = this.isDone() ? "T" : "F";
        return String.format("%s~deadline %s /by %s %s\n", status, name,
                deadlineDay.toString(), deadlineTime.toString());
    }

    @Override
    public String toString() {
        String tick = this.isDone() ? "[✓]" : "[✗]";
        return String.format("%s%s %s (by: %s %s)", SYMBOL, tick, name,
                deadlineDay.toString(), deadlineTime.toString());
    }
}
