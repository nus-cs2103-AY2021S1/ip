public class Deadlines extends Task {

    private String deadline;

    public Deadlines(String description) {
        super(description);
        this.type = "Deadlines";
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String toString() {
        return "  [D][" + this.getStatusIcon() + "] "
                + this.getDescription().substring(0,description.indexOf("/")) + "(by: " + this.getDeadline() + ")";
    }
}
