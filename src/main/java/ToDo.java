public class ToDo extends Task {
    ToDo(String description) {
        this(description, false);
    }

    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Return updated task of subtype: ToDo
     *
     * @param isDone New status for the task
     * @return new ToDo with updated status
     */
    @Override
    public Task updateStatus(boolean isDone) {
        return new ToDo(super.description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
