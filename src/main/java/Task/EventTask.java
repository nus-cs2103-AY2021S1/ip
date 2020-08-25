package Task;

import Exception.DateTimeInvalidFormatException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.PatternSyntaxException;

public class EventTask extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("kk:mm");
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public EventTask(String description, String dateTimeString) throws DateTimeInvalidFormatException {
        super(description);

        try {
            // dateTimeString will be in  "YYYY-MM-DD kkmm-kkmm" format
            String[] arr = dateTimeString.split(" ");
            int year = Integer.parseInt(arr[0].substring(0, 4));
            int mth = Integer.parseInt(arr[0].substring(5, 7));
            int day = Integer.parseInt(arr[0].substring(8));

            int startHour = Integer.parseInt(arr[1].substring(0, 2));
            int startMin = Integer.parseInt(arr[1].substring(2, 4));

            int endHour = Integer.parseInt(arr[1].substring(5, 7));
            int endMin = Integer.parseInt(arr[1].substring(7));

            if (endHour < startHour || (endHour == startHour && endMin < startMin)) {
                throw new DateTimeInvalidFormatException(
                        "Action invalid. End Time less than Start Time.\n"
                );
            }

            LocalDate date = LocalDate.of(year, mth, day);
            LocalTime startTime = LocalTime.of(startHour, startMin);
            LocalTime endTime = LocalTime.of(endHour, endMin);

            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
        } catch(NumberFormatException nfe) {
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm-HHmm"
            );
        } catch (IndexOutOfBoundsException ioobe) {
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm-HHmm"
            );
        } catch (PatternSyntaxException pse) {
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm-HHmm"
            );
        } catch (DateTimeException dte) { // invalid date time eg 9999-13-32 2400
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time value invalid.\n"
            );
        }
    }

    public EventTask(String description, String dateTimeString, boolean isDone)
            throws DateTimeInvalidFormatException {
        super(description, isDone);

        try {
            // dateTimeString will be in  "YYYY-MM-DD HHmm-HHmm" format
            String[] arr = dateTimeString.split(" ");
            int year = Integer.parseInt(arr[0].substring(0, 4));
            int mth = Integer.parseInt(arr[0].substring(5, 7));
            int day = Integer.parseInt(arr[0].substring(8));

            int startHour = Integer.parseInt(arr[1].substring(0, 2));
            int startMin = Integer.parseInt(arr[1].substring(2, 4));

            int endHour = Integer.parseInt(arr[1].substring(5, 7));
            int endMin = Integer.parseInt(arr[1].substring(7));

            if (endHour < startHour || (endHour == startHour && endMin < startMin)) {
                throw new DateTimeInvalidFormatException(
                        "Action invalid. End Time less than Start Time.\n"
                );
            }

            LocalDate date = LocalDate.of(year, mth, day);
            LocalTime startTime = LocalTime.of(startHour, startMin);
            LocalTime endTime = LocalTime.of(endHour, endMin);

            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
        } catch(NumberFormatException nfe) {
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm-HHmm"
            );
        } catch (IndexOutOfBoundsException ioobe) {
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm-HHmm"
            );
        } catch (PatternSyntaxException pse) {
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time Format incorrect.\n"
                            + "     Correct Format: YYYY-MM-DD HHmm-HHmm"
            );
        } catch (DateTimeException dte) { // invalid date time eg 9999-13-32 2400
            throw new DateTimeInvalidFormatException(
                    "Action invalid. Date and Time value invalid.\n"
            );
        }
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDateInput() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String getTimeInput() {
        return this.startTime.format(DateTimeFormatter.ofPattern("kkmm"))
                + "-"
                + this.endTime.format(DateTimeFormatter.ofPattern("kkmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(EventTask.DATE_FORMAT) + " "
                + this.startTime .format(EventTask.TIME_FORMAT)+ "-"
                + this.endTime.format(EventTask.TIME_FORMAT) + ")";
    }
}
