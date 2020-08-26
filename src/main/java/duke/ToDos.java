package duke;

import java.util.ArrayList;

/**
 *
 */
public class ToDos extends Task {
    /**
     *
     * @param taskTitle
     * @param isDone
     */
    public ToDos(String taskTitle, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.TODO);
    }

    /**
     *
     * @param taskTitle
     * @param isDone
     * @param tasks
     */
    public static void loadTodoTask(String taskTitle, Boolean isDone, ArrayList<Task> tasks) {
        ToDos todoTask = new ToDos(taskTitle, isDone);
        tasks.add(todoTask);
    }

    /**
     *
     * @return
     */
    @Override
    public String writeToFile() {
        return super.writeToFile();
    }
}
