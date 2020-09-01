/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String deadline;

    Deadline(String desc) throws MissingDeadlineException {
        super("D", desc.split(" /by ", 2)[0]);
        String[] temp = desc.split(" /by ", 2);
        if (temp.length == 1) {
            throw new MissingDeadlineException("deadline");
        } else {
            String date = desc.split(" /by ", 2)[1];
            this.deadline = containsTime(date)
                                ? formatDateTime(date)
                                : formatDate(date);
        }
    }

    Deadline(String desc, String date) {
        super("D", desc);
        deadline = date;
    }


    @Override
    public String formatTaskForFile() {
        return taskType + " | " + (isDone ? "1" : "0") + " | " +
                description + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + " (by: " + deadline + ")";
    }
}
