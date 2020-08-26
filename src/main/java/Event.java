import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
        protected LocalDate date;
        protected Date timeStart;
        protected Date timeEnd;

    Event(String description, String dateAndTime, TaskType taskType) {
        super(description, taskType);
        String[] dateAndTimeSplit = dateAndTime.split(" ");
        String[] dateSplit = dateAndTimeSplit[0].split("/");
        this.date = LocalDate.of(Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[0]));
        String[] timeSplit = dateAndTimeSplit[1].split("-");
        String timeStartUnformatted = timeSplit[0];
        String timeStartFormatted = timeStartUnformatted.substring(0, 2)
                + ":"
                + timeStartUnformatted.substring(2, 4);
        String timeEndUnformatted = timeSplit[1];
        String timeEndFormatted = timeEndUnformatted.substring(0, 2)
                + ":"
                + timeEndUnformatted.substring(2, 4);
        SimpleDateFormat _24HourFormat = new SimpleDateFormat("HH:mm");
        try {
            this.timeStart = _24HourFormat.parse(timeStartFormatted);
            this.timeEnd = _24HourFormat.parse(timeEndFormatted);
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
        return _12HourFormat.format(timeStart)
                + " to "
                + _12HourFormat.format(timeEnd);
    }

    public boolean hasSameDateAs(LocalDate d1) {
        return this.date.equals(d1);
    }

    @Override
    public String toString() {
        return "[Event]" + super.toString()
                + "(at: " + this.printDate() + " " + this.printTime() + ")";
    }
}
