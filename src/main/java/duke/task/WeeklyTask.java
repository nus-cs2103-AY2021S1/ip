package duke.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public class WeeklyTask extends Task {
    protected String every;
    protected LocalDateTime atDateTime;

    public WeeklyTask(String description, String every, boolean isDone) {
        super(description, isDone);
        this.every = every;
        try {
            this.atDateTime = getTaskNextInstanceDateTime();
        } catch (DateTimeParseException e) {
            System.out.println("Invalid input! Enter appropriate date and time format");
        }
    }

    public String toString() {
        return "[Weekly] " + this.description + " (at: "
                + this.atDateTime.format(DateTimeFormatter.ofPattern("MMM d yyy HH:mm")) + ")";
    }

    /**
     * Encode task into a String to be saved in text file.
     *
     * @return String of encoded task details.
     */
    public String toEncoding() {
        return "W>0>" + this.description + ">" + this.every;
    }

    private LocalDateTime getTaskNextInstanceDateTime() {
        String[] everySplit = every.split(" ", 2);
        String day = everySplit[0];
        String time = everySplit[1];
        String hour = time.substring(0, 2);
        String min = time.substring(2);
        int intHour = Integer.parseInt(hour);
        int intMin = Integer.parseInt(min);
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MMM d yyy HH:mm");
        LocalDate now = LocalDate.now();
        LocalDate nextDate = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDateTime nextDateTime = nextDate.atTime(intHour, intMin);
        return nextDateTime;
    }

    public boolean isDate(LocalDate dateFilter) {
        return this.atDateTime.toLocalDate().equals(dateFilter);
    }
}
