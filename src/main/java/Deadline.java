import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task{
    protected LocalDate date;
    protected Date time;

    Deadline(String description, String dateAndTime, TaskType taskType) {
        super(description, taskType);
        String[] dateAndTimeSplit = dateAndTime.split(" ");
        String[] dateSplit = dateAndTimeSplit[0].split("/");
        this.date = LocalDate.of(Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[0]));
        String timeUnformatted = dateAndTimeSplit[1];
        String timeFormatted = timeUnformatted.substring(0, 2)
                + ":"
                + timeUnformatted.substring(2, 4);
        SimpleDateFormat _24HourFormat = new SimpleDateFormat("HH:mm");
        try {
            this.time = _24HourFormat.parse(timeFormatted);
        } catch (ParseException e) {
            System.out.println("Hmm that didn't work... Is your time in HHMM format?");
        }
    }

    public String printDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        return dateTimeFormatter.format(date);
    }

    public String printTime() {
        SimpleDateFormat _12HourFormat = new SimpleDateFormat("hh:mm a");
        return _12HourFormat.format(time);
    }

    @Override
    public String toString() {

        return "[Deadline]"
                + super.toString()
                + "(by: " + this.printDate() + " " + this.printTime() + ")";
    }
}
