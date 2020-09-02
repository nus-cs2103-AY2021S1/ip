package dukechatbot.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import dukechatbot.enums.TaskEnum;

/**
 * Represents EventTask object 
 * and parses the start and end time to another format.
 */
public class EventTask extends Task {

    private static final DateTimeFormatter PATTERN_DATE_INPUT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter PATTERN_TIME_INPUT =
            DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter PATTERN_DATE_OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    private static final DateTimeFormatter PATTERN_TIME_OUTPUT =
            DateTimeFormatter.ofPattern("KK:mm a");

    private String title;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    public EventTask(String title, String date, String startTime, String endTime) {
        super(String.format("%s (at: %s %s - %s)", title,
                LocalDate.parse(date, PATTERN_DATE_INPUT)
                        .format(PATTERN_DATE_OUTPUT),
                LocalTime.parse(startTime, PATTERN_TIME_INPUT)
                        .format(PATTERN_TIME_OUTPUT),
                LocalTime.parse(endTime, PATTERN_TIME_INPUT)
                        .format(PATTERN_TIME_OUTPUT)), TaskEnum.EVENT);
        this.title = title;
        this.date = LocalDate.parse(date, PATTERN_DATE_INPUT);
        this.startTime = LocalTime.parse(startTime, PATTERN_TIME_INPUT);
        this.endTime = LocalTime.parse(endTime, PATTERN_TIME_INPUT);
    }

    public EventTask(String title, String date, String startTime, String endTime, boolean isDone) {
        super(String.format("%s (at: %s %s - %s)", title,
                LocalDate.parse(date, PATTERN_DATE_INPUT)
                        .format(PATTERN_DATE_OUTPUT),
                LocalTime.parse(startTime, PATTERN_TIME_INPUT)
                        .format(PATTERN_TIME_OUTPUT),
                LocalTime.parse(endTime, PATTERN_TIME_INPUT)
                        .format(PATTERN_TIME_OUTPUT)), isDone, TaskEnum.EVENT);
        this.title = title;
        this.date = LocalDate.parse(date, PATTERN_DATE_INPUT);
        this.startTime = LocalTime.parse(startTime, PATTERN_TIME_INPUT);
        this.endTime = LocalTime.parse(endTime, PATTERN_TIME_INPUT);
    }

    /**
     * Returns the save format of the task.
     * 
     * @return Save format of the task.
     */
    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s | %s", TaskEnum.EVENT.getTaskLetter(),
                super.getIsDone() ? 1 : 0, title, this.getDateTimeDetails());
    }

    private String getDateTimeDetails() {
        return String.format("%s %s-%s",
                this.date.format(PATTERN_DATE_INPUT),
                this.startTime.format(PATTERN_TIME_INPUT),
                this.endTime.format(PATTERN_TIME_INPUT));
    }
}
