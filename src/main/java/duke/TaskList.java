package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static TaskList createTaskList() {
        return new TaskList(new ArrayList<Task>());
    }

    public Task updateTaskStatus(int index, boolean status) {
        Task taskToUpdate = tasks.get(index);
        Task updatedTask = taskToUpdate.updateStatus(true);
        tasks.set(index, updatedTask);
        return updatedTask;
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task removeTask(int task) {
        return tasks.remove(task);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

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
