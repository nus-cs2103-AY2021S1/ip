public class Event extends Task {
    private static final String DELIMITER = " /at ";
    private String dateInfo;

    private Event(String description, String dateInfo) {
        super(description);
        this.dateInfo = dateInfo;
    }

    public static Event create(String args) {
        String[] argsList = args.split(DELIMITER);
        if (argsList.length < 2) {
            throw new TaskException("Not enough arguments");
        } else {
            return new Event(argsList[0], argsList[1]);
        }
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | E | " + description + " | At: " + dateInfo;
    }
}
