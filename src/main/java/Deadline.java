import java.time.LocalDate;
import java.time.LocalTime;


public class Deadline extends Task{
    protected LocalDate date;
    protected LocalTime time;

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
        this.time = LocalTime.parse(timeFormatted);
    }

    Deadline(String description, TaskType taskType, boolean isDone, LocalDate date, LocalTime time) {
        super(description, taskType, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {

        return "[Deadline]"
                + super.toString()
                + "(by: " + this.date + " " + this.time + ")";
    }
}
