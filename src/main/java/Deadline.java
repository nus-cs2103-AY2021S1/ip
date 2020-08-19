public class Deadline extends Task {
    protected String deadline;

    Deadline(String desc) throws MissingDeadlineException {
        super(desc.split(" /by ", 2)[0]);
        String[] temp = desc.split(" /by ", 2);
        if (temp.length == 1) {
            throw new MissingDeadlineException("deadline");
        } else {
            this.deadline = desc.split(" /by ", 2)[1];
        }
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + this.deadline + ")";
    }
}
