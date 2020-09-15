package duke.tool;

import duke.exception.MissingDeadlineException;
import duke.exception.MissingTaskException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks stored in the object.
     *
     * @return The current list of tasks.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list stored in the object.
     *
     * @return The size of the current list of tasks.
     */
    public int getLength() {
        return tasks.size();
    }

    Task createTask(String taskType, String desc) throws MissingDeadlineException{
        assert taskType != null && desc != null;
        if (taskType.equals("todo")) {
            return new Todo(desc);
        } else if (taskType.equals("deadline")) {
            return new Deadline(desc);
        } else {
            return new Event(desc);
        }
    }

    /**
     * Adds a task with a description and if applicable, a deadline to the task list
     * and returns it.
     *
     * @param taskType The type of task to be added.
     * @param desc The description of task to be added.
     * @return The task added to the task list.
     * @throws MissingDeadlineException
     */
    public Task addTask(String taskType, String desc) throws MissingDeadlineException {
        assert taskType != null && desc != null;
        Task task = createTask(taskType, desc);
        tasks.add(task);
        return task;
    }

    String formatTask(int num, List<Task> tasks) {
        assert num >= 0;
        String lineBreak = num != tasks.size() - 1 ? "\n  " : "";
        return (num + 1) + "." + tasks.get(num) + lineBreak;
    }

    /**
     * Returns the tasks in a numbered order as a String that can be printed out.
     *
     * @return The String containing the tasks in a numbered order.
     */
    public String formattedList() {
        String list = "";

        for (int i = 0; i < tasks.size(); i++) {
            list += formatTask(i, tasks);
        }

        return list;
    }

    /**
     * Marks the task at the given index as completed and returns it.
     *
     * @param num The index of the task in the list.
     * @return The completed task.
     * @throws MissingTaskException
     */
    public Task completeTask(int num) throws MissingTaskException {
        if (num > 0 && num <= tasks.size()) {
            Task task = tasks.get(num - 1);
            task.completeTask();
            return task;
        } else {
            throw new MissingTaskException(num);
        }
    }

    /**
     * Deletes the task at the given index and returns it.
     *
     * @param num The index of the task in the list.
     * @return The deleted task.
     * @throws MissingTaskException
     */
    public Task deleteTask(int num) throws MissingTaskException {
        if (num > 0 && num <= tasks.size()) {
            Task task = tasks.remove(num - 1);
            return task;
        } else {
            throw new MissingTaskException(num);
        }
    }

    /**
     * Find all possible tasks whose description matches the given keyword.
     *
     * @param keyword A string to be checked against the tasks' description.
     * @return
     */
    public String findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.match(keyword)) {
                matchingTasks.add(task);
            }
        }

        String list = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            list += formatTask(i, matchingTasks);
        }
        return list;
    }
}
