import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime timeStart;
    protected LocalTime timeEnd;

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
        this.timeStart = LocalTime.parse(timeStartFormatted);
        this.timeEnd = LocalTime.parse(timeEndFormatted);
    }

    Event(String description, TaskType taskType, boolean isDone, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        super(description, taskType, isDone);
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "[Event]" + super.toString()
                + "(at: " + this.date + " " + this.timeStart + "-" + this.timeEnd + ")";
    }
}
