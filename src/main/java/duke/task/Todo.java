package duke.task;

public class Todo extends Task {

    public Todo(String todo) {
        super(todo);
    }

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
