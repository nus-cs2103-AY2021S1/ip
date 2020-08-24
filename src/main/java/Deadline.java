import java.time.LocalDateTime;

public class Deadline extends Task {
    private static final String DELIMITER_COMMAND = " /by ";

    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) throws TaskException {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isCompleted) throws TaskException {
        super(description, isCompleted);
        this.deadline = LocalDateTime.parse(deadline);
    }

    public static Deadline create(String args) throws TaskException {
        String[] argsList = args.split(DELIMITER_COMMAND);
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            LocalDateTime date = LocalDateTime.parse(argsList[1], Task.READER_FORMAT);
            return new Deadline(argsList[0], date);
        }
    }

    public static Deadline parseStorageString(String storageString) throws TaskException {
        String[] inputList = storageString.split(DELIMITER_STORAGE);
        if (inputList.length < 3) {
            throw new TaskException("Invalid storage string");
        }
        return new Deadline(inputList[0], inputList[1], decodeCompletionFlag(inputList[2]));
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toStorageString() {
        return description + DELIMITER_STORAGE + deadline + DELIMITER_STORAGE + storeCompletionFlag();
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | D | " + description + " | By: " + deadline.format(Task.DISPLAY_FORMAT);
    }
}
