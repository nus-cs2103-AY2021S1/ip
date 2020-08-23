public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by){
        super(description);
        this.isDone = isDone;
        this.by = by;
    }

    public String saveText(){
        return "D | " + getStatusIcon() + " | " + description + " | " + by + "\n";
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
