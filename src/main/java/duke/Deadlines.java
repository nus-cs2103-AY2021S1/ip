package duke;

import java.time.LocalDate;

public class Deadlines extends Task {

    private String deadline;
    private LocalDate date;

    /**
     * Class constructor with description of task.
     * @param description
     */
    public Deadlines(String description) {
        super(description);
        this.type = "Deadlines";
    }

    /**
     * Sets the Deadline to the desired String.
     * @param deadline the deadline.
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns the deadline.
     * @return deadline in the form of a String.
     */
    public String getDeadline() {
        return this.deadline;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Sets the LocalDate of the Deadline.
     */
    public void setDateTime() {
        int index = this.deadline.indexOf(" ");
        String dateTemp = this.deadline.substring(0, index);
        LocalDate date = LocalDate.parse(dateTemp);
        this.date = date;
    }

    /**
     * Returns string description of deadline.
     * @return String description of deadline
     */
    public String toString() {
        return "  [D][" + this.getStatusIcon() + "] "
                + this.getDescription().substring(0, description.indexOf("/")) + "(by: "
                + this.getDeadline() + ")" + this.recurringPrinter();
    }
}
