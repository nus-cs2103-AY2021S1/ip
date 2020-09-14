package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the task list contains the user's tasks.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Gets a task corresponding to its index in the list.
     *
     * @param index The index of the task in the list.
     * @return The corresponding task.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the user's task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Gets the task list.
     *
     * @return The user's task list.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Forms a new task list object containing all the tasks with the given keyword.
     *
     * @param keyword The keyword from the user's input.
     * @return A new task list object with all the tasks contaning that keyword.
     */
    public TaskList findTaskWithKeyword(String keyword) {
        List<Task> taskWithKeyword = new ArrayList<>();
        for (Task task : taskList) {
            if (task.hasKeyword(keyword)) {
                taskWithKeyword.add(task);
            }
        }
        return new TaskList(taskWithKeyword);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task taskToBeDeleted = taskList.get(index);
        taskList.remove(taskToBeDeleted);
    }

    /**
     * Get the task list that is sorted alphabetically.
     *
     * @return The task list that is sorted based on the alphabetical order of the tasks.
     */
    public TaskList sortDescription() {
        List<Task> sortedList = new ArrayList<>(this.taskList);
        sortedList.sort((task1, task2) -> task1.getDescription().compareTo(task2.getDescription()));
        return new TaskList(sortedList);
    }

    /**
     * Get the task list that is sorted based on the priority level.
     *
     * @return The task list that is sorted based on the priority level of the tasks.
     */
    public TaskList sortPriority() {
        List<Task> sortedList = new ArrayList<>(this.taskList);
        sortedList.sort((task1, task2) ->
                task1.getPriority().getEquivalentNumber() - task2.getPriority().getEquivalentNumber());
        return new TaskList(sortedList);
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            int taskNumber = i + 1;
            result = result + taskNumber + "." + taskList.get(i).toString()
                    + (i == taskList.size() - 1 ? "" : "\n");
        }
        return result;
    }
}
