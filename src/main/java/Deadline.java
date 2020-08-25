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

    @Override
    public String formatTaskForFile() {
        return this.taskType + " | " + (this.isDone ? "1" : "0") + " | " +
                this.description + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + this.deadline + ")";
    }
}
