public class Deadline extends Task{
    private final String by;
    private final String TAG = "[D]";

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescription() {
        String message = TAG + super.getDescription() + " " + this.getBy();
        return message;
    }

    public String getBy() {
        return "(by: " + this.by + ")";
    }

}
