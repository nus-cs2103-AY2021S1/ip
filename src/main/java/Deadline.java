public class Deadline extends Task{
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, int isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString().replace("[\u2718]", "[D][\u2718]") + " (by: " + time + ")";
    }

    @Override
    public String deleteMessage() {
        return super.deleteMessage().replace("[\u2718]", "[T][\u2718]");
    }

    public String data() {
        return  "D" + super.data() + " | " + time;
    }

}
