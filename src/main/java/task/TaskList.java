package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * TaskList stores a list of tasks and
 * the corresponding operations on it.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Get the task in the list by its id.
     *
     * @param taskId the displayed id in the list.
     * @return the task associated with id.
     */
    public Task getTaskById(int taskId) {
        int index = taskId - 1;
        return this.tasks.get(index);
    }

    /**
     * Add a new task to the list of tasks.
     *
     * @param newTask the new task to be added.
     * @return a new TaskList containing all the old tasks and
     * the new task.
     */
    public TaskList addTask(Task newTask) {
        List<Task> newTaskList = this.tasks;
        newTaskList.add(newTask);
        return new TaskList(newTaskList);
    }

    /**
     * Set a task with input id in the list as done.
     *
     * @param taskId the displayed id in the list.
     * @return a new TaskList containing all the update tasks.
     */
    public TaskList markAsDone(int taskId) {
        Task currentTask = this.getTaskById(taskId);
        Task doneTask = currentTask.complete();

        int index = taskId - 1;
        List<Task> newTaskList = this.tasks;
        newTaskList.set(index, doneTask);
        return new TaskList(newTaskList);
    }

    /**
     * Print all the tasks.
     *
     * @return a String shows all tasks in a formatted way.
     */
    public String printTasks() {
        String result = "Here are the tasks in your list:";

        result += IntStream.range(0, this.getSize())
                .mapToObj((id) -> String.format("\n%d.%s", id + 1, tasks.get(id)))
                .reduce((a, b) -> a + b)
                .orElse("");

        return result;
    }

}
