package task;

/**
 * Task that marks a to-do.
 */
public class ToDo extends Task {

    /**
     * Creates new To Do task.
     *
     * @param msg Stored message for to do.
     */
    public ToDo(String msg) {
        super(msg);
    }

    @Override
    public String getDataString() {
        return String.format("T | %s\n", super.getDataString());
    }

    /**
     * Returns a formatted string representing the to do.
     *
     * @return Formatted string of the to do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    @Override
    public boolean equals(Object obj) {

        if (obj instanceof ToDo) {
            return super.equals(obj);
        }
        return false;
    }
}
