package tasks;

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
                        .format(PATTERN_OUTPUT)), "D");

        this.title = title;
        this.dateTime = LocalDateTime.parse(dateTimeDetails,
                PATTERN_INPUT);
    }

    public DeadlineTask(String title, String dateTimeDetails, boolean isDone) {
        super(String.format("%s (by: %s)", title, dateTimeDetails), isDone, "D");
        this.title = title;
        this.dateTimeDetails = dateTimeDetails;
    }

    @Override
    public String getSaveFormat() {
        return String.format("D | %s | %s | %s",
                super.getIsDone() ? 1 : 0, title, dateTimeDetails);
    }
}
