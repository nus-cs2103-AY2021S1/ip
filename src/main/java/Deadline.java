public class Deadline extends Task {
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
        taskType = TaskType.DEADLINE;
    }

    @Override
    public String getDesc() {
        return desc + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String generateSaveFileData() {
        return "D|" + (isDone ? "1" : "0") + "|" + desc + "|" + by;
    }

}
