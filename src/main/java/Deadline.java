import java.time.LocalDate;

public class Deadline extends Task {
    private final String ddl;
    private LocalDate date = null;

    Deadline(String name, String ddl) {
        super(name);
        this.ddl = ddl;
        try {
            date = LocalDate.parse(ddl);
        }
        catch (Exception e) {
            date = null;
        }
    }

    @Override
    public String toString() {
        String status = String.format("[D][%s] ", (super.done ? "✓" : "✗"));
        String time = date == null
                ? String.format(" (by: %s)", ddl)
                : " (by: " + DateParser.format(date) + ")";
        return status + this.getName() + time;
    }

}
