public class Deadline extends Task {

    String date;

    Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + this.date + ")";
    }
}
