
import java.time.LocalDateTime;

public class Event extends Task {
    private static final String DELIMITER = " /at ";
    private LocalDateTime dateInfo;

    private Event(String description, LocalDateTime dateInfo) {
        super(description);
        this.dateInfo = dateInfo;
    }

    public static Event create(String args) {
        String[] argsList = args.split(DELIMITER);
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            LocalDateTime date = LocalDateTime.parse(argsList[1], Task.READER_FORMAT);
            return new Event(argsList[0], date);
        }
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | E | " + description + " | At: " + dateInfo.format(Task.DATE_FORMAT);
    }
}
