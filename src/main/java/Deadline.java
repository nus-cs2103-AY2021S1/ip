public class Deadline extends Task {
    private String ddl;

    Deadline(String name, String ddl) {
        super(name);
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        String status = String.format("[D][%s] ", (super.done ? "✓" : "✗"));
        String time = String.format(" (by: %s)", ddl);
        return status + this.getName() + time;
    }

}
