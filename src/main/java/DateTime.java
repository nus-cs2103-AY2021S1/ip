import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private LocalDate datetime = LocalDate.now();
    private final String INVALIDDATEMESSAGE = "Your Date must be in the format YYYY-MM-DD. Initialising date to Today!";

    public DateTime(String datetime) {
        if (isValidFormat(datetime)) {
            this.datetime = LocalDate.parse(datetime);
        } else {
            System.out.println(INVALIDDATEMESSAGE);
        }
    }

    public static Boolean isValidFormat(String date) {
        String[] components = date.split("-");
        return components.length == 3
                && components[0].length() == 4
                && components[1].length() == 2
                && Integer.parseInt(components[1]) <= 12
                && components[2].length() == 2
                && Integer.parseInt(components[2]) <= 31;
    }

    @Override
    public String toString() {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}