public class Deadline extends Task {
    protected String deadline;

    public Deadline(String deadline) {
        super(deadline.substring(9, deadline.indexOf("/")-1));
        this.deadline = "by: " + deadline.substring(deadline.indexOf("/")+4);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]").append(super.toString()).append(" (").append(this.deadline).append(")");
        return sb.toString();
    }
}
