import java.time.LocalDateTime;

public class Event extends Task {
    public static final String STORE_EVENT = "E";

    private static final String DELIMITER = " /at ";

    private LocalDateTime dateInfo;

    public Event(String description, LocalDateTime dateInfo) throws TaskException {
        super(description);
        this.dateInfo = dateInfo;
    }

    public Event(String description, String dateInfo, boolean isComplete) throws TaskException {
        super(description, isComplete);
        this.dateInfo = LocalDateTime.parse(dateInfo);
    }

    public static Event create(String args) throws TaskException {
        String[] argsList = args.split(DELIMITER);
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            LocalDateTime date = LocalDateTime.parse(argsList[1], Task.READER_FORMAT);
            return new Event(argsList[0], date);
        }
    }

    @Override
    public String toStorageString() {
        return STORE_EVENT + " | " + description + " | " + dateInfo + " | " + getCompletionFlagStorage();
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | E | " + description + " | At: " + dateInfo.format(Task.DISPLAY_FORMAT);
    }
}
