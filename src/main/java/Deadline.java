import java.time.LocalDate;
import java.time.LocalTime;

<<<<<<< HEAD
/**
 * Supports the creation of Deadline objects.
 */
public class Deadline extends Task{
=======
public class Deadline extends Task {
>>>>>>> branch-A-CodingStandard
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates a Deadline object
     * @param description Description of task.
     * @param dateAndTime Date and time of the task.
     * @param taskType Type of task.
     */
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

    /**
     * Creates a Deadline object with extra parameters that defines date and time separately, and whether
     * task is done or not.
     * @param description Description of task.
     * @param taskType Type of task.
     * @param isDone Whether task is done or not.
     * @param date Date of task.
     * @param time Time that task is due.
     */
    Deadline(String description, TaskType taskType, boolean isDone, LocalDate date, LocalTime time) {
        super(description, taskType, isDone);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns string representation of the Deadline object.
     *
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[Deadline]"
                + super.toString()
                + "(by: " + this.date + " " + this.time + ")";
    }
}
