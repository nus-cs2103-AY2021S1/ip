import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String identifier;
    private LocalDate date;

    public Deadline(String description) {
        super(description);
        this.identifier = "D";

        String[] desArray = this.description.split("/", 2); //e.g. do homework/ by 1999-10-21
        String[] dateArray = desArray[1].split(" ", 2); //1999-10-21
        this.date = LocalDate.parse(dateArray[1]);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    //separates date from description
    @Override
    public String getDescription() {
        String fullDescription = super.getDescription();
        String[] desArray = fullDescription.split("/", 2);
        return desArray[0];
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + "(" + timeArray[0] + ": " + getDate() + ")";
    }
}