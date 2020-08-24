package tasks;

import exceptions.DataException;

public class Deadline extends Task {

    // task must be done by this time
    private final String by;

    public Deadline(String desc, String by) throws DataException {
        super(desc);
        if (by.isBlank()) {
            throw new DataException("Time Due", "Cannot be blank");
        }
        this.by = by;
    }

    @Override
    protected char getTaskType() {
        return 'D';
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }

    @Override
    public String getParentCommand() {
        return "deadline " + getDescription() + " /by " + by;
    }
}
