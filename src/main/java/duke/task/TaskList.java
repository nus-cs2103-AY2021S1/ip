package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import duke.DukeException;

/** TaskList stores a list of tasks and the corresponding operations on it */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the number of elements in the task list.
     *
     * @return The number of elements in the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the task in the list by its id.
     *
     * @param taskId The displayed id in the list.
     * @return The Task associated with id.
     */
    public Task getTaskById(int taskId) {
        int index = taskId - 1;
        return this.tasks.get(index);
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param newTask The new task to be added.
     * @return A new TaskList containing all the old tasks and the new task.
     */
    public TaskList addTask(Task newTask) {
        List<Task> newTaskList = this.tasks;
        newTaskList.add(newTask);
        return new TaskList(newTaskList);
    }

    /**
     * Deletes a task in the list via its taskId.
     *
     * @param taskId The displayed id in the list.
     * @return A new TaskList containing all the update tasks.
     */
    public TaskList deleteTask(int taskId) {
        int index = taskId - 1;
        List<Task> newTaskList = this.tasks;
        newTaskList.remove(index);
        return new TaskList(newTaskList);
    }

    /**
     * Sets a task with input id in the list as done.
     *
     * @param taskId The displayed id in the list.
     * @return A new TaskList containing all the update tasks.
     * @throws DukeException If the task is already done.
     */
    public TaskList markAsDone(int taskId) throws DukeException {
        Task currentTask = this.getTaskById(taskId);
        if (currentTask.isDone) {
            throw new DukeException("☹ OOPS!!! This task is already done!");
        } else {
            Task doneTask = currentTask.complete();

            int index = taskId - 1;
            List<Task> newTaskList = this.tasks;
            newTaskList.set(index, doneTask);
            return new TaskList(newTaskList);
        }
    }

    /**
     * Prints all the tasks.
     *
     * @return A String shows all tasks in a formatted way.
     */
    public String printTasks() {
        String result = IntStream.range(0, this.getSize())
                .mapToObj((id) -> String.format("\n%d.%s", id + 1, tasks.get(id)))
                .reduce((a, b) -> a + b)
                .orElse("");

        return result;
    }

    /**
     * Formats the tasks in task list to the style specified in level-7.
     *
     * @return A list of string, each representing a formatted task in the task list.
     */
    public List<String> formatTaskList() {
        List<String> formattedTaskList = new ArrayList<>();
        for (Task t: this.tasks) {
            formattedTaskList.add(t.format());
        }
        return formattedTaskList;
    }

    /**
     * Finds all the tasks containing the keyword
     *
     * @param keyword The keyword which must be present in the desired task name.
     * @return A String including all the tasks found whose name containing the keyword.
     */
    public String findTasksByKeyword(String keyword) {
        return IntStream.range(0, this.getSize())
                .mapToObj((id) -> String.format("\n%d.%s", id + 1, tasks.get(id)))
                .filter((s) -> s.contains(keyword))
                .reduce((a, b) -> a + b)
                .orElse("");
    }
}
