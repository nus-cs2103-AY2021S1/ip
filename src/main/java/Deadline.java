import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String dateTime;
    private LocalDate dueDate;
    private String input;

    Deadline(String item, String dateTime, LocalDate dueDate, String input) {
        super(item);
        this.dateTime = dateTime;
        this.dueDate = dueDate;
        this.input = input;
    }

    public static LocalDate formatDate(String dateTime) throws DukeException {
        try {
            String[] split = dateTime.split(" ");
            String[] dayMonthYear = split[0].split("/");
            String date = dayMonthYear[2] + "-" + dayMonthYear[1] + "-";
            if (dayMonthYear[0].length() == 1) {
                date += "0" + dayMonthYear[0];
            } else {
                date += dayMonthYear[0];
            }
            LocalDate dueDate = LocalDate.parse(date);
            return dueDate;
        } catch (Exception e) {
            throw new DukeException("Incorrect format of date. It should be: DD/MM/YYYY 2359");
        }
    }

    public static String formatDateString(LocalDate dateTime, String dueDate) throws DukeException {
        try {
            String time = "2359";
            String[] split = dueDate.split(" ");
            if (split.length == 2) {
                time = split[1];
            }
            String format = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return format + " " + formatTime(time);
        } catch (Exception e) {
            throw new DukeException("Incorrect format of date. It should be: DD/MM/YY time");
        }
    }

    public static String formatTime(String time) {
        int firstHour = (int) time.charAt(0) - '0';
        int secondHour = (int) time.charAt(1) - '0';
        int hours = firstHour * 10 + secondHour;
        String amPM;
        String convertedTime;
        if (hours < 12) {
            amPM = "am";
        } else {
            amPM = "pm";
        }
        hours = hours % 12;
        if (hours == 0) {
            convertedTime = "12";
        } else {
            convertedTime = Integer.toString(hours);
        }
        return convertedTime + "." + time.substring(2, 4) + amPM;
    }

    @Override
    public String getItem() {
        return "[D]" + super.getItem() + "(by: " + dateTime + ")";
    }
}
