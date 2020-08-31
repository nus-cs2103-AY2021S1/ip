package duke.datetime;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtility {
    private static DateTimeFormatter outputDateFmt = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static DateTimeFormatter outputDateTimeFmt = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a");

    public static DateTimeFormat checkDateTimeType(String dateStr) {
        try {
            LocalDateTime.parse(dateStr);
            return DateTimeFormat.DateTime;
        } catch (DateTimeException e) {}
        try {
            LocalDateTime.parse(dateStr, outputDateTimeFmt);
            return DateTimeFormat.DateTime;
        } catch (DateTimeException e) {}
        try {
            LocalDate.parse(dateStr);
            return DateTimeFormat.Date;
        } catch (DateTimeException e) {}
        try {
            LocalDate.parse(dateStr, outputDateFmt);
            return DateTimeFormat.Date;
        } catch (DateTimeException e) {}

        return DateTimeFormat.String;
    }

    public static String formatString(String dateStr, DateTimeFormat format) {
        switch(format) {
            case DateTime:
                try {
                    return LocalDateTime.parse(dateStr).format(outputDateTimeFmt);
                } catch (DateTimeException e) {}
            case Date:
                try {
                    return LocalDate.parse(dateStr).format(outputDateFmt);
                } catch (DateTimeException e) {}

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
                return DateTimeUtility.compare(LocalDate.parse(a, outputDateFmt), LocalDate.parse(b, outputDateFmt));
            } else if (bType == DateTimeFormat.DateTime) {
                return DateTimeUtility.compare(LocalDate.parse(a, outputDateFmt), LocalDateTime.parse(b, outputDateTimeFmt));
            } else {
                throw new DateTimeException("Cannot compare Datetime with string!");
            }
        } else if (aType == DateTimeFormat.DateTime) {
            if (bType == DateTimeFormat.Date) {
                return DateTimeUtility.compare(LocalDateTime.parse(a, outputDateFmt), LocalDate.parse(b, outputDateFmt));
            } else if (bType == DateTimeFormat.DateTime) {
                return DateTimeUtility.compare(LocalDateTime.parse(a, outputDateTimeFmt), LocalDateTime.parse(b, outputDateTimeFmt));
            } else {
                throw new DateTimeException("Cannot compare Datetime with string!");
            }
        } else {
            throw new DateTimeException("Cannot compare Datetime with string!");
        }
    }
}
