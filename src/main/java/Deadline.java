import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    private LocalDate ddl = null;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.ddl = LocalDate.parse(by);
            this.by = ddl.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception exception) {
            System.out.println("Sorry I can't understand the time format you just inputted. \n" +
                    "Try it in this format: yyyy-mm-dd (e.g. 2020-01-01)," +
                    " so that I can remind you at the time");
        }
    }

    @Override
    public String savedFormat() {
        return "D " + super.savedFormat() + String.format(" | %s", this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
