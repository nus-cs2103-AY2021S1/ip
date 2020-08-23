public class Deadline extends Task implements Saveable {
    String deadline;

    Deadline(String label, String deadline, boolean done) {
        super(label, done);
        // Remove the "by"
        this.deadline = deadline;
    }

    @Override
    public String getInfo() {
        StringBuilder str = new StringBuilder();
        str.append("D");
        str.append(super.getInfo());
        str.append(super.separator);
        str.append(deadline);
        return str.toString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
