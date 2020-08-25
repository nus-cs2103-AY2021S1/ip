import java.util.Date;
public class DeadlineTask extends Task {
    Date time;
    public DeadlineTask(
            Boolean isDone,
            String name,
            Date time
    ) {
        super(isDone, name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D],%s,%s", super.toString(), time);
    }
}
