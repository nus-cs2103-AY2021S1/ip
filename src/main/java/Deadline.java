public class Deadline extends Task {

    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline parse(String[] split) {
        Deadline deadline = new Deadline(split[2], split[3]);
        if (split[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }

    public String serialize() {
        return "D|" + super.serialize() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
