public class Deadline extends Task {
    String dateBy;
    public Deadline(String s) {
        super(s.substring(0, s.lastIndexOf('/')));
        this.dateBy = s.substring(s.lastIndexOf('/') + 3);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dateBy + ")";
    }
}
