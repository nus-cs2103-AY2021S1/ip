public class Deadline extends Task {
    String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String getStatusIcon() {
        return String.format("[D]%s", super.getStatusIcon(), time);
    }

    @Override
    public String getOutput() {
        return String.format("%s %s%s", getStatusIcon(), this.description, this.time);
    }

}
