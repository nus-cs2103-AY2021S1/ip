public class Deadlines extends Task {
    private String by;

    Deadlines(String task , String by) {
        super(task, TaskType.DEADLINE);
        this.by = by;
    }
    @Override
    public String toString() {
        return String.format("%s(by:%s)", super.toString(), this.by);
    }
}
