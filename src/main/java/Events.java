import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDate at;

    public Events(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public static Events load(String taskDetails) {
        String[] splitTaskDetails = taskDetails.split("\\|",4);
        for (int i = 0; i < splitTaskDetails.length; i++) {
            splitTaskDetails[i] = splitTaskDetails[i].strip();
        }
        Events event = new Events(splitTaskDetails[2],
                LocalDate.parse(splitTaskDetails[3]));
        if (splitTaskDetails[1].equals("true")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String saveAs() {
        return "E | " + super.saveAs() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
    }
}