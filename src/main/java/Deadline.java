public class Deadline extends Task {
    private final String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public Deadline(String name, String time, boolean doneState) {
        super(name, doneState);
        this.time = time;
    }

    @Override
    public String write() {
        return String.format("D,%s%s", time, super.write());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }
}
