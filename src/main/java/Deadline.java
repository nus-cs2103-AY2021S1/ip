public class Deadline extends Task {

    private String date;

    public Deadline(String name, String date) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date);
    }
}
