package duke.task;

public class Todo extends Task {

    /**
     * Constructs a todo object.
     * @param todo Name of todo object.
     */
    public Todo(String todo) {
        super(todo);
    }

    /**
     * Returns the todo in array form.
     * @return String array.
     */
    @Override
    public String[] taskToArray() {
        String done;
        if(this.isCompleted()) {
            done = "0";
        } else {
            done = "1";
        }
        String[] str = new String[]{"T", done, this.getTaskName()};
        return str;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
