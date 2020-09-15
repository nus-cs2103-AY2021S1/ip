public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String printTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append(super.printTask());
        sb.append(" (by: " + this.deadline + ")");
        return sb.toString();
    }

}