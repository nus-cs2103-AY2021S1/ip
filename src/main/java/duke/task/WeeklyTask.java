package duke.task;

import duke.exception.DukeException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

public class WeeklyTask extends Task {
    private String every;
    private LocalDateTime atDateTime;

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
     * Encodes task into a String to be saved in text file.
     * @return String of encoded task details.
     */
    public String toEncoding() {
        return "W>0>" + this.description + ">" + this.every;
    }

    /**
     * Gets the date and time of the next instance of the weekly task.
     * @return LocalDateTime containing the date and time of the next instance of the weekly task.
     */
    private LocalDateTime getTaskNextInstanceDateTime() throws DukeException {
        // String every has to be split into day and time.
        String[] everySplit = this.every.split(" ", 2);
        String day = everySplit[0];
        String time = everySplit[1];

        // String time has to be split into hour and minute.
        String hour = time.substring(0, 2);
        String min = time.substring(2);

        // Strings hour and min need to be converted to int so that they can be used by Java LocalDate API.
        int intHour = Integer.parseInt(hour);
        int intMin = Integer.parseInt(min);

        LocalDate nowDate = LocalDate.now();

        if (taskIsToday(nowDate, day, intHour, intMin)) {
            LocalDateTime nextDateTime = nowDate.atTime(intHour, intMin);
            return nextDateTime;
        }

        LocalDate nextDate = getNextDate(day, nowDate);
        LocalDateTime nextDateTime = nextDate.atTime(intHour, intMin);
        return nextDateTime;
    }

    /**
     * Checks if task is on a specified date.
     * @param dateFilter the specified date.
     * @return boolean whether the task is due on the date.
     */
    public boolean isDate(LocalDate dateFilter) {
        return this.atDateTime.toLocalDate().equals(dateFilter);
    }

    /**
     * Determines the date of the next instance of the weekly task.
     * @param day the day that task is on.
     * @param now the current LocalDate.
     * @return the LocalDate of the next instance of the weekly task.
     * @throws DukeException when invalid day is input by user.
     */
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

    /**
     * Checks if the next instance of weekly tasks is today.
     * @param nowDate the current LocalDate.
     * @param day the day of weekly task.
     * @param intHour the hour of day of the weekly task.
     * @param intMin the minute of day of the weekly task.
     * @return boolean whether the next instance of task is today.
     */
    private boolean taskIsToday(LocalDate nowDate, String day, int intHour, int intMin) {
        if (nowDate.getDayOfWeek().toString().toLowerCase().equals(day)) {
            LocalTime nowTime = LocalTime.now();
            LocalTime taskTime = LocalTime.of(intHour, intMin);
            if (nowTime.isBefore(taskTime)) {
                return true;
            }
        }
        return false;
    }
}
