public class Deadline extends Task  {
    private String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }

    @Override
    public String toStoreFormat(){
        String type = "D";
        String status = isDone ? "1" : "0";
        String connect = " | ";
        return type + connect + status + connect + description + connect + time;
    }
}
