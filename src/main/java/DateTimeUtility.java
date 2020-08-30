import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtility {
    public static DateTimeFormat checkDateTimeType(String dateStr) {
        try {
            LocalDateTime.parse(dateStr);
            return DateTimeFormat.DateTime;
        } catch (DateTimeException e) {}
        try {
            LocalDate.parse(dateStr);
            return DateTimeFormat.Date;
        } catch (DateTimeException e) {}

        return DateTimeFormat.String;
    }

    public static String formatString(String dateStr, DateTimeFormat format) {
        switch(format) {
            case DateTime:
                return LocalDateTime.parse(dateStr).format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a"));
            case Date:
                return LocalDate.parse(dateStr).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            default:
                return dateStr;
        }
    }
    public static String formatString(String dateStr) {
        return DateTimeUtility.formatString(dateStr, DateTimeUtility.checkDateTimeType(dateStr));
    }

    public static int compare(LocalDate a, LocalDate b) {
        return a.compareTo(b);
    }

    public static int compare(LocalDateTime a, LocalDateTime b) {
        return a.compareTo(b);
    }

    public static int compare(LocalDate a, LocalDateTime b) {
        return a.compareTo(b.toLocalDate());
    }

    public static int compare(LocalDateTime a, LocalDate b) {
        return a.toLocalDate().compareTo(b);
    }

    public static int compare(String a, String b) throws DateTimeException {
        DateTimeFormat aType = DateTimeUtility.checkDateTimeType(a);
        DateTimeFormat bType = DateTimeUtility.checkDateTimeType(b);

        //check all possible cases!
        if (aType == DateTimeFormat.Date) {
            if (bType == DateTimeFormat.Date) {
                return DateTimeUtility.compare(LocalDate.parse(a), LocalDate.parse(b));
            } else if (bType == DateTimeFormat.DateTime) {
                return DateTimeUtility.compare(LocalDate.parse(a), LocalDateTime.parse(b));
            } else {
                throw new DateTimeException("Cannot compare Datetime with string!");
            }
        } else if (aType == DateTimeFormat.DateTime) {
            if (bType == DateTimeFormat.Date) {
                return DateTimeUtility.compare(LocalDateTime.parse(a), LocalDate.parse(b));
            } else if (bType == DateTimeFormat.DateTime) {
                return DateTimeUtility.compare(LocalDateTime.parse(a), LocalDateTime.parse(b));
            } else {
                throw new DateTimeException("Cannot compare Datetime with string!");
            }
        } else {
            throw new DateTimeException("Cannot compare Datetime with string!");
        }
    }
}
