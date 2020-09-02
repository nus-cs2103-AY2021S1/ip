package dukechatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dukechatbot.enums.TaskEnum;

/**
 * Represents Deadline Task and
 * parses input datetime info to another format.
 */
public class DeadlineTask extends Task {

    private static final DateTimeFormatter PATTERN_INPUT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static final DateTimeFormatter PATTERN_OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy KK:mm a");

    private String title;

    private LocalDateTime dateTime;

    public DeadlineTask(String title, String dateTimeDetails) throws DateTimeParseException {
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

    /**
     * Returns the save format of the task.
     * 
     * @return Save format of the task.
     */
    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s | %s", TaskEnum.DEADLINE.getTaskLetter(),
                super.getIsDone() ? 1 : 0, title, this.getDateTimeDetails());
    }

    private String getDateTimeDetails() {
        return this.dateTime.format(PATTERN_INPUT);
    }
}
