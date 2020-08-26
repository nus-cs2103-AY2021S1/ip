public class Deadline extends Task {
    private String dueTime;

    Deadline(String name, String dueTime) {
        super(name, "D");
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueTime);
    }

    @Override
    public String getAbbreviatedString() {
        int isDoneRep = this.done ? 1 : 0;
        return String.format("%s | %d | %s | %s", this.type, isDoneRep, this.name, this.dueTime);
    }
}
