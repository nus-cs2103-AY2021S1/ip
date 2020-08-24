import java.time.LocalDateTime;

public class Event extends Task {
    public static final String STORE_EVENT = "E";

    private static final String DELIMITER_COMMAND = " /at ";
    private static final String DELIMITER_STORAGE = " //| ";

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
        String[] argsList = args.split(DELIMITER_COMMAND);
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            LocalDateTime date = LocalDateTime.parse(argsList[1], Task.READER_FORMAT);
            return new Event(argsList[0], date);
        }
    }

    public static Event parseStorageString(String storageString) throws TaskException {
        String[] inputList = storageString.split(DELIMITER_STORAGE);
        if (inputList.length < 3) {
            throw new TaskException("Invalid storage string");
        }
        return new Event(inputList[0], inputList[1], decodeCompletionFlag(inputList[2]));
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public String toStorageString() {
        return description + DELIMITER_STORAGE + dateInfo + DELIMITER_STORAGE + storeCompletionFlag();
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | E | " + description + " | At: " + dateInfo.format(Task.DISPLAY_FORMAT);
    }
}
