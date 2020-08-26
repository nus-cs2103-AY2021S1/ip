public class Deadline extends TaskDDL {

    public Deadline(String task, String ddl) {
        super(task, ddl);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", ddl);
    }

    @Override
    public String fileString() {
        return String.format("D|%s|%s", super.fileString(), ddl);
    }
}
