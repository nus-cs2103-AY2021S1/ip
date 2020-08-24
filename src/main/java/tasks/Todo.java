package tasks;

import exceptions.DataException;

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
