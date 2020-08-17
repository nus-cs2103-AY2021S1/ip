public class Deadline extends Task {
    private String type = "[D]";
    private String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }
}
