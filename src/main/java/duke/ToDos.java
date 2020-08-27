package duke;

import java.util.ArrayList;

/**
 * Handles To-Do tasks.
 */
public class ToDos extends Task {
    /**
     * ToDos class constructor.
     *
     * @param taskTitle A string of To-Do task name.
     * @param isDone Status of the To-Do task.
     */
    public ToDos(String taskTitle, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.TASK_TYPE_TODO);
    }

    /**
     * Loads To-Do tasks from the file at beginning.
     *
     * @param taskTitle A string of To-Do task name.
     * @param isDone Status of the To-Do task.
     * @param tasks The overall user's task list.
     */
    public static void loadTodoTask(String taskTitle, Boolean isDone, ArrayList<Task> tasks) {
        ToDos todoTask = new ToDos(taskTitle, isDone);
        tasks.add(todoTask);
    }

    /**
     * Returns a string follows the format of the file.
     * @return A string follows the format of the file.
     */
    @Override
    public String writeToFile() {
        return super.writeToFile();
    }
}
