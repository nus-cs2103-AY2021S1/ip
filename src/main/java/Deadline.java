public class Deadline extends Task {
    String deadline;

    Deadline(String s, String deadline) {
        super(s);
        this.deadline = deadline;
    }

    Deadline(String name, boolean completed, String deadline) {
        super(name, completed);
        this.deadline = deadline;
    }

    @Override
    String getType() {
        return "deadline";
    }

    @Override
    String getTime() {
        return deadline;
    }

    @Override
    Deadline completeTask() {
        return new Deadline(this.name, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[deadline]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
