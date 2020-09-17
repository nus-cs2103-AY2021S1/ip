public class Todo extends Task {

    /**
     * Contructor for a todoTask.
     * @param name Description of the todoTask.
     * @throws DukeEmptyDescException Exception thrown when the todoTask is empty.
     */
    public Todo(String name) throws DukeEmptyDescException {
        super(name, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
