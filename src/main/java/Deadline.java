
public class Deadline extends Task {
    private String byTime;

    public Deadline(String deadlineTask, String byTime) {
        super(deadlineTask);
        this.byTime = byTime;
    }

    @Override
    public String[] taskToArray() {
        String done;
        if(this.isCompleted()) {
            done = "0";
        } else {
            done = "1";
        }
        String[] str = new String[]{"D", done, this.getTaskName(), byTime};
        return str;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime + ")";
    }
}
