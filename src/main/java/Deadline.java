public class Deadline extends Task {

    String date;

    Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    Deadline(String task, boolean done, String date) {
        super(task);
        this.done = done;
        this.date = date;
    }

    @Override
    String getSaveString() {
        return "[D] " + super.getSaveString() + " /by " + this.date;
    }

    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + this.date + ")";
    }
}
