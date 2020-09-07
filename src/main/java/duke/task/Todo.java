package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        final String TODO_TASK_STRING =  "[T]" + super.toString();
        return TODO_TASK_STRING;
    }

    /**
     * generates string of task to save in storage file.
     * @return string of task.
     */
    @Override
    public String stringToSave() {
        char status = this.isDone ? '1' : '0';
        final String TODO_TASK_TO_SAVE = "T " + "| " + status + " | " + this.description;
        return TODO_TASK_TO_SAVE;
    }
}