import java.time.LocalDateTime;

public class Deadline extends Task {
    private static final String DELIMITER = " /by ";
    private LocalDateTime deadline;

    private Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
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
    public String toString() {
        return printCompletionFlag() + " | D | " + description + " | By: " + deadline.format(Task.DATE_FORMAT);
    }
}
