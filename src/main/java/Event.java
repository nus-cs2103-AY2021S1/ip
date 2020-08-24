import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    
    private String at;
    private LocalDate taskDate;
    private LocalTime taskTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "E";

        String[] dateArguments = at.split(" ");

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
    
    public Event(String uniqueId, boolean isDone, String description, String at) {
        super (uniqueId, isDone, description);
        this.at = at;
        this.taskType = "E";

        String[] dateArguments = at.split(" ");

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
        return at;
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
        return "[E]" + super.toString() + " (at: )" + formatTaskTime();
    }
}