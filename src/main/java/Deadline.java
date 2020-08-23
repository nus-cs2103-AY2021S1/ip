import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task implements Saveable {
    LocalDate deadline;

    Deadline(String label, String deadline, boolean done) {
        super(label, done);
        // Remove the "by"
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns a minimalistic string representation of the task
     * @return A string that describes the task.
     */

    @Override
    public String getInfo() {
        StringBuilder str = new StringBuilder();
        str.append("D");
        str.append(super.getInfo());
        str.append(super.separator);
        str.append(deadline.toString());
        return str.toString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
