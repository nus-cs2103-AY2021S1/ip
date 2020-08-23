public class Deadlines extends Task {

    protected String deadline;

    public Deadlines(String taskDesc, String deadline) {
        super(taskDesc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "+ deadline.substring(3) + ")";
    }
}
