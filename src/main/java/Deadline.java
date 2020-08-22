public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws WrongFormatException {
        super(description, "[D]", "deadline", false);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) throws WrongFormatException {
        super(description, "[D]", "deadline", isDone);
        this.by = by;
    }

    @Override
    public String stringToSaveInMemory() {
        return super.stringToSaveInMemory() + "|" + by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}