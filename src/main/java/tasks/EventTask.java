package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private static final DateTimeFormatter PATTERN_DATE_INPUT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter PATTERN_TIME_INPUT =
            DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter PATTERN_DATE_OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    private static final DateTimeFormatter PATTERN_TIME_OUTPUT =
            DateTimeFormatter.ofPattern("KK:mm a");

    String title;

    LocalDate date;

    LocalTime startTime;

    LocalTime endTime;

    public EventTask(String title, String date, String startTime, String endTime) {
        super(String.format("%s (at: %s %s - %s)", title,
                LocalDate.parse(date, PATTERN_DATE_INPUT)
                        .format(PATTERN_DATE_OUTPUT),
                LocalTime.parse(startTime, PATTERN_TIME_INPUT)
                        .format(PATTERN_TIME_OUTPUT),
                LocalTime.parse(endTime, PATTERN_TIME_INPUT)
                        .format(PATTERN_TIME_OUTPUT)), "E");
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
                        .format(PATTERN_TIME_OUTPUT)), isDone, "E");
        this.title = title;
        this.date = LocalDate.parse(date, PATTERN_DATE_INPUT);
        this.startTime = LocalTime.parse(startTime, PATTERN_TIME_INPUT);
        this.endTime = LocalTime.parse(endTime, PATTERN_TIME_INPUT);
    }

    @Override
    public String getSaveFormat() {
        return String.format("E | %s | %s | %s",
                super.getIsDone() ? 1 : 0, title, this.getDateTimeDetails());
    }

    private String getDateTimeDetails() {
        return String.format("%s %s-%s",
                this.date.format(PATTERN_DATE_INPUT),
                this.startTime.format(PATTERN_TIME_INPUT),
                this.endTime.format(PATTERN_TIME_INPUT));
    }
}