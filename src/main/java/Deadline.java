import java.time.LocalDateTime;

public class Deadline extends Task {
    public static final String STORE_DEADLINE = "D";

    private static final String DELIMITER = " /by ";
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = LocalDateTime.parse(deadline);
    }

    public static Deadline create(String args) {
        String[] argsList = args.split(DELIMITER);
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            LocalDateTime date = LocalDateTime.parse(argsList[1], Task.READER_FORMAT);
            return new Deadline(argsList[0], date);
        }
    }

    @Override
    public String toStorageString() {
        return STORE_DEADLINE + " | " + description + " | " + deadline + " | " + getCompletionFlagStorage();
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | D | " + description + " | By: " + deadline.format(Task.DATE_FORMAT);
    }
}
