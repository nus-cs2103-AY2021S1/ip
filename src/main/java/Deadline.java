import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    
    private String by;
    private LocalDate taskDate;
    private LocalTime taskTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";

        String[] dateArguments = by.split(" ");

        try {
            taskDate = LocalDate.parse(dateArguments[0]);

            if (dateArguments.length == 2) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH[:]mm");
                taskTime = LocalTime.parse(dateArguments[1], dtf);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Date and time format is invalid.");
        }
    }
    
    public Deadline(String uniqueId, boolean isDone, String description, String by) {
        super(uniqueId, isDone, description);
        this.by = by;
        this.taskType = "D";

        String[] dateArguments = by.split(" ");

        try {
            taskDate = LocalDate.parse(dateArguments[0]);

            if (dateArguments.length == 2) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH[:]mm");
                taskTime = LocalTime.parse(dateArguments[1], dtf);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Date and time format is invalid.");
        }
    }
    
    public String getTime() {
        return by;
    }

    private String formatTaskTime() {
        String output = taskDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        if (taskTime != null) {
            output += ", " + taskTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        }
        return output;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: )" + formatTaskTime();
    }
}
