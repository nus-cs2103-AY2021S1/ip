package clippy.task;

public class Event extends Task {
    private String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
        taskType = TaskType.EVENT;
    }

    @Override
    public void updateTime(String newTime) {
        this.at = newTime;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() +  " (at: " + at + ")";
    }

    @Override
    public String generateSaveFileData() {
        return "E|" + (isDone ? "1" : "0") + "|" + desc + "|" + at;
    }
}
