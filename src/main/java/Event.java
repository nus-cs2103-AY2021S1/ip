public class Event extends Task{
    public String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(boolean done, String description, String due) {
        super(done, description,'E');
        this.time = due;
        String unparseMessage = "E";
        if (done) {
            unparseMessage += "1";
        } else {
            unparseMessage += "0";
        }
        unparseMessage += description;
        unparseMessage += ",";
        unparseMessage += due;
        super.unparseMessage = unparseMessage;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + time + ")";
    }
}
