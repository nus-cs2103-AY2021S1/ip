package alfred;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {

    private LocalDate date;
    private LocalTime time;

    private DateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Parses DateTime input and returns a new DateTime object.
     *
     * @param input DateTime entered by user to create task.
     * @return New DateTime object.
     * @throws AlfredException If DateTime format is invalid.
     */
    public static DateTime parseFromString(String input) throws AlfredException {
        String[] datetime = input.split(" ");

        LocalDate date;
        LocalTime time = null;

        try {
            date = LocalDate.parse(datetime[0]);

            if (datetime.length == 2) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH[:]mm");
                time = LocalTime.parse(datetime[1], dtf);
            }

        } catch (DateTimeParseException e) {
            throw new AlfredException("DateTime format is invalid.");
        }

        return new DateTime(date, time);
    }


    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
