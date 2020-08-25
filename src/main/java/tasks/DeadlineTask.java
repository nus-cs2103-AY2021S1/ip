package tasks;

import enums.TaskEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private static final DateTimeFormatter PATTERN_INPUT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static final DateTimeFormatter PATTERN_OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy KK:mm a");

    String title;

    LocalDateTime dateTime;

    public DeadlineTask(String title, String dateTimeDetails) {
        super(String.format("%s (by: %s)", title,
                LocalDateTime.parse(dateTimeDetails, PATTERN_INPUT)
                        .format(PATTERN_OUTPUT)), TaskEnum.DEADLINE);

        this.title = title;
        this.dateTime = LocalDateTime.parse(dateTimeDetails,
                PATTERN_INPUT);
    }

    public DeadlineTask(String title, String dateTimeDetails, boolean isDone) {
        super(String.format("%s (by: %s)", title,
                LocalDateTime.parse(dateTimeDetails, PATTERN_INPUT)
                        .format(PATTERN_OUTPUT)), isDone, TaskEnum.DEADLINE);
        this.title = title;
        this.dateTime = LocalDateTime.parse(dateTimeDetails, PATTERN_INPUT);
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s | %s", TaskEnum.DEADLINE.getTaskLetter(),
                super.getIsDone() ? 1 : 0, title, this.getDateTimeDetails());
    }

    private String getDateTimeDetails() {
        return this.dateTime.format(PATTERN_INPUT);
    }
}
