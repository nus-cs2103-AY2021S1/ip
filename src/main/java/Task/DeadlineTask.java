package task;

import exception.DateTimeInvalidFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.PatternSyntaxException;

public class DeadlineTask extends Task {
    protected LocalDateTime dateTime;
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy kk:mm");
    public DeadlineTask(String description, String dateTimeString) throws DateTimeInvalidFormatException {
        super(description);

        try {
            String[] arr = dateTimeString.split(" "); // dateTimeString will be in  "YYYY-MM-DD HHmm" format
            int year = Integer.parseInt(arr[0].substring(0, 4));
            int mth = Integer.parseInt(arr[0].substring(5, 7));
            int day = Integer.parseInt(arr[0].substring(8));

            int hour = Integer.parseInt(arr[1].substring(0, 2));
            int min = Integer.parseInt(arr[1].substring(2));

            LocalDateTime dateTime = LocalDateTime.of(year, mth, day, hour, min);
            this.dateTime = dateTime;
        } catch(NumberFormatException nfe) { // Integer cannot parse string
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm"
            );
        } catch (IndexOutOfBoundsException ioobe) { // String formatted different from expected
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm"
            );
        } catch (PatternSyntaxException pse) { // No space between date and time
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm"
            );
        } catch (DateTimeException dte) { // invalid date time eg 9999-13-32 2400
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time value invalid.\n"
            );
        }
    }

    public DeadlineTask(String description, String dateTimeString, boolean isDone)
        throws DateTimeInvalidFormatException {
        super(description, isDone);

        try {
            String[] arr = dateTimeString.split(" "); // dateTimeString will be in  "YYYY-MM-DD HHmm" format
            int year = Integer.parseInt(arr[0].substring(0, 4));
            int mth = Integer.parseInt(arr[0].substring(5, 7));
            int day = Integer.parseInt(arr[0].substring(8));

            int hour = Integer.parseInt(arr[1].substring(0, 2));
            int min = Integer.parseInt(arr[1].substring(2));

            LocalDateTime dateTime = LocalDateTime.of(year, mth, day, hour, min);
            this.dateTime = dateTime;
        } catch(NumberFormatException nfe) { // Integer cannot parse string
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm"
            );
        } catch (IndexOutOfBoundsException ioobe) { // String formatted different from expected
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm"
            );
        } catch (PatternSyntaxException pse) { // No space between date and time
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm"
            );
        } catch (DateTimeException dte) { // invalid date time eg 9999-13-32 2400
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time value invalid.\n"
            );
        }
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDateInput() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String getTimeInput() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("kkmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(DeadlineTask.DATE_TIME_FORMAT) + ")";
    }
}
