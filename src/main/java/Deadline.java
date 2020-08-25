public class Deadline extends Task {
    String dueDate;

    public Deadline(String name, Status status, String dueDate) {
        super(name, status);
        this.dueDate = dueDate;
    }

    @Override public String toString() {
        return "[D] " + this.status.statusToSymbol() + this.name + " by: " + dueDate;
    }

}
