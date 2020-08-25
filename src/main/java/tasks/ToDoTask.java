package tasks;

import enums.TaskEnum;

public class ToDoTask extends Task {

    public ToDoTask(String title) {
        super(title, TaskEnum.TODO);
    }

    public ToDoTask(String title, boolean isDone) {
        super(title, isDone, TaskEnum.TODO);
    }
}
