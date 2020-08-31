package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    final static private DateTimeFormatter MYDATEFORMATTER =
            DateTimeFormatter.ofPattern("LLL dd uuuu");
    protected String by;
    protected String byFormat;
    protected LocalDate date;


    public Deadline(String name, String by) {
        super(name);
        this.by = by;
        this.date = LocalDate.parse(by);
        this.byFormat = this.date.format(MYDATEFORMATTER);
    }

    @Override
    public String getParsedData() {
        return "D" + "/" + String.valueOf(super.done) + "/" + super.name + "/" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byFormat + ")";
    }
}
