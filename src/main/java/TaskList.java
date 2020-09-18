import java.util.ArrayList;

/**
 * Represents a list of all tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int totalTasks;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasks = tasksList;
        this.totalTasks = tasksList.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.totalTasks = 0;
    }

    /**
     * Adds a task to the list of tasks.
     * @param task task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        totalTasks++;
    }

    /**
     * Checks whether the list of tasks contains a task.
     * @param task the task to be checked.
     * @return boolean value indicating if the list contains the task.
     */
    public boolean containsTask(Task task) {
        boolean isDuplicate = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDescription().equals(task.getDescription())) {
                if (task instanceof Deadline && currTask instanceof Deadline) {
                    if (((Deadline) currTask).getDueDate().equals(((Deadline) task).getDueDate())) {
                        isDuplicate = true;
                    }
                } else if (task instanceof Event && currTask instanceof Event) {
                    if (((Event) currTask).getEventDate().equals(((Event) task).getEventDate())) {
                        isDuplicate = true;
                    }
                } else if (task instanceof Todo){
                    isDuplicate = true;
                }
            }
        }
        return isDuplicate;
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskNumber number of task to be deleted.
     */
    public void delete(int taskNumber) {
        tasks.remove(taskNumber - 1);
        totalTasks--;
    }

    /**
     * Marks a task as done.
     * @param taskNumber number of task to be marked as done.
     */
    public void markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Returns a specific task.
     * @param taskNumber number of task to be returned.
     * @return a specific task.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Returns the total number of tasks.
     * @return total number of tasks.
     */
    public int getTotalTasks() {
        return this.totalTasks;
    }

    /**
     * Returns the list of tasks.
     * @return list of tasks.
     */
    public ArrayList<Task> getTasksList() {
        return this.tasks;
    }

    /**
     * Gets the filtered tasks based on the search keyword.
     * @param filter search keyword to filter the tasks.
     * @return list of filtered tasks.
     */
    public ArrayList<Task> listFilteredTasks(String filter) {
        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (int i = 0; i < totalTasks; i++) {
            if (tasks.get(i).description.contains(filter)) {
                filteredTasks.add(tasks.get(i));
            }
        }
        return filteredTasks;
    }
}
