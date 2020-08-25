public class DeadlineTask extends Task {
    String time;
    public DeadlineTask(
            Boolean isDone,
            String name,
            String time
    ) {
        super(isDone, name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]|%s|%s", super.toString(), time);
    }
}
