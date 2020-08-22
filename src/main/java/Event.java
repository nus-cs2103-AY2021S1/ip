public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }

    public Event(String description, String date, boolean isDone) {
        super(description, isDone, TaskType.TODO);
        this.date = date;
    }
    
    @Override
    public String getData() {
        return String.format("%s_%s ", super.getData(), date);
    } 

    @Override
    public String toString() {
        return super.toString() + " (at: " + date + ")";
    }
}
