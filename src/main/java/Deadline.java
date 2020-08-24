public class Deadline extends Task {
    public static final String STORE_DEADLINE = "D";

    private static final String DELIMITER = " /by ";
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    public static Deadline create(String args) {
        String[] argsList = args.split(" /by ");
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            return new Deadline(argsList[0], argsList[1]);
        }
    }

    @Override
    public String toStorageString() {
        return STORE_DEADLINE + " | " + description + " | " + deadline + " | " + getCompletionFlagStorage();
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | D | " + description + " | By: " + deadline;
    }
}
