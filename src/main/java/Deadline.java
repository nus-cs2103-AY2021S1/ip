public class Deadline extends Task {

    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        this.tag = "D";
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        return String.format("[%s][%s] %s (by: %s)",tag,symbol,taskName,by);
    }
}