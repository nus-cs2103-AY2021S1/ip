import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateAndTime {
    private final LocalDate date;
    private final LocalTime time;

    public DateAndTime(String date, String time) throws InvalidDateTimeException {
        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            System.out.print("lol");
            throw new InvalidDateTimeException();
        }
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.UK))
                + " "
                + this.time.format(DateTimeFormatter.ofPattern("hh:mma"));
    }

}
