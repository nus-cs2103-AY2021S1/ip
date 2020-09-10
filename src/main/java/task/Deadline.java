package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    final static private DateTimeFormatter MY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("LLL dd uuuu");
    protected String by;
    protected String byFormat;
    protected LocalDate date;


    public Deadline(String name, String by) {
        super(name);
        this.by = by;
        this.date = LocalDate.parse(by);
        this.byFormat = this.date.format(MY_DATE_FORMATTER);

    }

    @Override
    public String getParsedData() {
        String[] args = new String[]{"D", String.valueOf(super.isDone), super.tag, super.name, this.by};
        return String.join(Task.DELIMITER, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.equals("")) {
            tag = Task.TAG_ICON + super.tag;
        }
        return "[D]" + super.toString() + " (by: " + this.byFormat + ")" + tag;
    }
}
