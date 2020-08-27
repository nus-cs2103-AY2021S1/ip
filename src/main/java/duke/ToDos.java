package duke;

import java.util.ArrayList;

public class ToDos extends Task {
    public ToDos(String taskTitle, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.TASK_TYPE_TODO);
    }

    public static void loadTodoTask(String taskTitle, Boolean isDone, ArrayList<Task> tasks) {
        ToDos todoTask = new ToDos(taskTitle, isDone);
        tasks.add(todoTask);
    }

    @Override
    public String writeToFile() {
        return super.writeToFile();
    }
}
