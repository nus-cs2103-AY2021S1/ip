import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final String description;
    private final LocalDate date;
    //private final LocalTime time;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.description = description;
        this.date = date;
        //this.time = time;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {

        return "[D]" + getStatusIcon() + this.description + "(by: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

