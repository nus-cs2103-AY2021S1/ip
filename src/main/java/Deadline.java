public class Deadline extends Task {

    private String deadline;
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + deadline + ")";
    }

    @Override
    public String toSave() {
        return "D " + super.toSave() + " | " + this.deadline;
    }
}
