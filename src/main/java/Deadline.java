public class Deadline extends Task {

    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String toTxtFormat() {
        return "D | " + super.toTxtFormat() + " | " + this.dateTime;
    }

    public static Deadline parse(String txtFormat, String[] txtArray) {
        Deadline deadline = new Deadline(txtArray[2].trim(), txtArray[3].trim());
        if (txtArray[1].trim().equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }
}
