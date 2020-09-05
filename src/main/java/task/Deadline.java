package task;

import java.time.LocalDate;

import utility.DateParser;

public class Deadline extends Task {
    private final String deadline;
    private LocalDate date = null;

    public Deadline(String name, String ddl) {
        super(name);
        this.deadline = ddl;
        try {
            date = LocalDate.parse(ddl);
        } catch (Exception e) {
            date = null;
        }
    }

    @Override
    public String toString() {
        String time = date == null
                ? String.format(" (by: %s)", deadline)
                : " (by: " + DateParser.format(date) + ")";
        return "[D]" + super.toString() + time;
    }

}
