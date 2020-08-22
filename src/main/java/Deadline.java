public class Deadline extends Task {
    protected String deadlineDate;

    public Deadline(String taskName, String deadlineDate) {
        super(taskName, "D");
        this.deadlineDate = deadlineDate;
    }
    
    public String getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + taskType + "][" + check + "] " + taskName + "(by:" + deadlineDate + ")";
    }
}

