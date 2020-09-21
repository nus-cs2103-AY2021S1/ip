import java.time.LocalDate;
import java.time.LocalTime;

// partial solution copied from iP
public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Executes the deadline command, causing add a task of type Deadline to the taskList,
     * provided that the command input is valid.
     *
     * @param description String describing the Deadline task.
     * @param dateBy String indicating the deadline of the Deadline task.
     * @param isDone Boolean indicating if the Deadline task is done.

     */
    public Deadline(String description, String dateBy, boolean isDone) {

        super(description.trim(), isDone);

        String[] dateAndTime = dateBy.trim().split(" ");
        String[] date = dateAndTime[0].split("/");

        String time;

        if (dateAndTime.length == 1) {
            time = "2359";
        } else {
            time = String.format("%04d", Integer.parseInt(dateAndTime[1]));
        }

        String day = date[0];
        String month = date[1];
        String year = date[2];

        this.date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);

        if (Integer.parseInt(hour) <= 23 && Integer.parseInt(minute) <= 59) {
            this.time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
        } else {
            this.time = LocalTime.of(23, 59);
        }
    }

    @Override
    public String toString() {
        String formattedTime = String.format("%02d", time.getHour()) + String.format("%02d", time.getMinute());
        return "  [D]" + super.toString() + " (By: " + date.getDayOfMonth() + " "
                + date.getMonth().toString().toLowerCase() + " " + date.getYear()
                + (time == null ? ")" : " " + formattedTime + " hrs)");
    }
}
