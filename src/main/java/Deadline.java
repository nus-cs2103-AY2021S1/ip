public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public Task fromString(String taskString) {
        boolean isDone = taskString.split(" ")[0].equals("[Done]");
        String description = taskString.split(" ")[2].split("\\s[(]by:\\s")[0];
        String by = taskString.split(" ")[2].split("\\s[(]by:\\s")[1];
        Deadline d = new Deadline(description, by);
        if (isDone) {
            d.setDone();
        }
        return d;
    }
}
