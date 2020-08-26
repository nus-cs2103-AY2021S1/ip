import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final String description;

    public Deadline(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String getDescription() {
        String[] desArray = this.description.split("/", 2);
        return desArray[0];
    }

    public String getTime() {
        String[] desArray = this.description.split("/", 2);
        return desArray[1];
    }

    @Override
    public String toString() {
        String[] descriptionArray = new String[3];
        String[] split1 = this.description.split("/by ", 2);
        String[] split2 = split1[1].split(" ", 2);

        descriptionArray[0] = split1[0];
        descriptionArray[1] = split2[0];
        descriptionArray[2] = split2[1];
        LocalDate d = LocalDate.parse(descriptionArray[1]);
        LocalTime t = LocalTime.parse(descriptionArray[2]);

        return "[D]" + getStatusIcon() + descriptionArray[0] + "(by: " +
                d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + t + ")";
    }
}