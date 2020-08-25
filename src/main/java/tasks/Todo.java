package tasks;

import exceptions.DataException;

/**
 * Todos have no additional information other than their description.
 * They are basically nothing more than a concrete implementation of a {@code Task}.
 */
public class Todo extends Task {

    public Todo(String desc) throws DataException {
        super(desc);
    }

    @Override
    protected char getTaskType() {
        return 'T';
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getParentCommand() {
        return "todo " + getDescription();
    }
}
