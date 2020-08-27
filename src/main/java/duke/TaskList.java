package duke;

import java.util.ArrayList;

/** Data structure for Tasks book keeping */
public class TaskList {
    private ArrayList<Task> tasks;

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static TaskList createTaskList() {
        return new TaskList(new ArrayList<Task>());
    }

    /**
     * Returns updated Task
     * Updates Task in TaskList and returns it
     *
     * @param index index of Task to update
     * @param status status to update Task
     * @return updated Task
     */
    public Task updateTaskStatus(int index, boolean status) {
        Task taskToUpdate = tasks.get(index);
        Task updatedTask = taskToUpdate.updateStatus(true);
        tasks.set(index, updatedTask);
        return updatedTask;
    }

    /**
     * Returns added Task to TaskList
     *
     * @param task Task to add to TaskList
     * @return Task added to TaskList
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Returns removed Task from TaskList
     *
     * @param task index of Task to remove
     * @return Task removed from TaskList
     */
    public Task removeTask(int task) {
        return tasks.remove(task);
    }

    /**
     * Returns Task
     *
     * @param i index of Task to retrieve
     * @return Task
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns length of TaskList
     *
     * @return length of TaskList
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Returns TaskList containing Tasks that contains the given string
     *
     * @param string string to check if Tasks contain it
     * @return TaskList with Task containing the string
     */
    public TaskList contains(String string) {
        TaskList foundTasks = TaskList.createTaskList();
        for (Task i : tasks) {
            if (i.description.contains(string)) {
                foundTasks.addTask(i);
            }
        }

        return foundTasks;
    }
}
