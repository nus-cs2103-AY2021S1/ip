import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate by;

    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static Deadlines load(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|",4);
        for (int i = 0; i < splitTaskDetails.length; i++) {
            splitTaskDetails[i] = splitTaskDetails[i].strip();
        }
        Deadlines deadline = new Deadlines(splitTaskDetails[2],
                LocalDate.parse(splitTaskDetails[3]));
        if (splitTaskDetails[1].equals("true")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String saveAs() {
        return "D | " + super.saveAs() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(
                DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}