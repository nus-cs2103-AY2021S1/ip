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

    /**
     * Get the task in the list by its id.
     *
     * @param task_id the displayed id in the list.
     * @return the task associated with id.
     */
    public Task getTaskById(int task_id) {
        int index = task_id - 1;
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
     * @param task_id the displayed id in the list.
     * @return a new TaskList containing all the update tasks.
     */
    public TaskList markAsDone(int task_id) {
        Task currentTask = this.getTaskById(task_id);
        Task doneTask = currentTask.complete();

        int index = task_id - 1;
        List<Task> newTaskList = this.tasks;
        newTaskList.set(index, doneTask);
        return new TaskList(newTaskList);
    }

    /**
     * Generate the feedback for an update of task status.
     *
     * @param task_id the displayed id in the list.
     * @return a String suggesting the completion of task update.
     */
    public String generateFeedbackById(int task_id) {
        Task currentTask = this.getTaskById(task_id);
        return "Nice! I've marked this task as done:\n" + currentTask;
    }

    /**
     * Print all the tasks.
     *
     * @return a String shows all tasks in a formatted way.
     */
    public String printTasks() {
        String result = "Here are the tasks in your list:";

        result += IntStream.range(0, tasks.size())
                .mapToObj((id) -> String.format("\n%d.%s", id + 1, tasks.get(id)))
                .reduce((a, b) -> a + b)
                .get();

        return result;
    }

}
