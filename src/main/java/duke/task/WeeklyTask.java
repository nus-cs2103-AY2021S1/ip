package duke.task;

import duke.exception.DukeException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

public class WeeklyTask extends Task {
    protected String every;
    protected LocalDateTime atDateTime;

    public WeeklyTask(String description, String every, boolean isDone) throws DukeException {
        super(description, isDone);
        this.every = every;
        try {
            this.atDateTime = getTaskNextInstanceDateTime();
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid input! Enter appropriate day and time format");
        }
    }

    public String toString() {
        String DateTime = this.atDateTime.format(DateTimeFormatter.ofPattern("MMM d yyy HH:mm"));
        return "[Weekly] " + this.description + " (at: " + DateTime + ")";
    }

    /**
     * Encode task into a String to be saved in text file.
     *
     * @return String of encoded task details.
     */
    public String toEncoding() {
        return "W>0>" + this.description + ">" + this.every;
    }

    /**
     * Gets the date and time of the next instance of the recurring task.
     *
     * @return LocalDateTime containing the date and time of the next instance of the recurring task.
     */
    private LocalDateTime getTaskNextInstanceDateTime() throws DukeException {
        String[] everySplit = every.split(" ", 2);
        String day = everySplit[0];
        String time = everySplit[1];
        String hour = time.substring(0, 2);
        String min = time.substring(2);
        int intHour = Integer.parseInt(hour);
        int intMin = Integer.parseInt(min);
        LocalDate now = LocalDate.now();
        //if (now.getDayOfWeek().toLowerCase().equals(day))
        LocalDate nextDate = getNextDate(day, now);
        LocalDateTime nextDateTime = nextDate.atTime(intHour, intMin);
        return nextDateTime;
    }

    public boolean isDate(LocalDate dateFilter) {
        return this.atDateTime.toLocalDate().equals(dateFilter);
    }

    private LocalDate getNextDate(String day, LocalDate now) throws DukeException {
        if (day.equals("monday")) {
            return now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        } else if (day.equals("tuesday")) {
            return now.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        } else if (day.equals("wednesday")) {
            return now.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        } else if (day.equals("thursday")) {
            return now.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        } else if (day.equals("friday")) {
            return now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } else if (day.equals("saturday")) {
            return now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        } else if (day.equals("sunday")) {
            return now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        } else {
            throw new DukeException("Please enter a valid day!");
        }
    }
}
