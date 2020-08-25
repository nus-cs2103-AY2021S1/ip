package duke;

public class Todo extends Task {

    public Todo (String task) {
        super(task, Tasktype.TODO, "");
    }

    public Todo (String task, boolean isDone) {
        super(task, Tasktype.TODO, "", isDone);
    }

}
