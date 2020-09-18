import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.function.Consumer;

/**
 * Encapsulates a TaskList object with an arraylist of tasks.
 */
public class TaskList {
    public ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public TaskList() {
        taskList = new ArrayList<>();
    }
    
    public int taskListLength() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list and returns the added task.
     * @param task the task to be added to the task list.
     * @return the task to be added.
     */
    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Updates the task and returns the updated task.
     * @param index the index of the task to be updated.
     * @param update the consumer to be applied to the task to be updated.
     * @return the task to be updated.
     * @throws DukeException throws an exception when the task index is out of range.
     */
    public Task updateTask(int index, Consumer<Task> update) throws DukeException {
        assert index > 0 : "index cannot be zero or negative";
        try {
            Task task = taskList.get(index);
            update.accept(task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            final String ERROR_MESSAGE = "ERROR: task index out of range";
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Deletes the task from the task list.
     * @param index the index of the task to be deleted.
     * @return the task to be deleted.
     * @throws DukeException throws an exception when the task index is out of range.
     */
    public Task deleteTask(int index) throws DukeException {
        return updateTask(index, task -> taskList.remove(task));
    }

    /**
     * Marks the task as done.
     * @param index the index of the task to be marked as done.
     * @return the task to be marked as done.
     * @throws DukeException throws an exception when the task index is out of range.
     */
    public Task doneTask(int index) throws DukeException {
       return updateTask(index, Task::markAsDone); 
    }
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Finds the tasks which matches the input in the task list.
     * @param display the user input.
     * @return a string representation of the tasks which matches the input.
     */
    public String findTask(String display) {
        assert display.length() > 0 : "input cannot be empty";
        StringBuilder matches = new StringBuilder();
        for (Task value : taskList) {
            if (value.description.contains(display)) {
                matches.append(value.toString()).append("\n");
            }
        }
        return matches.toString();
    }

    /**
     * Sorts all the tasks in the task list by date.
     */
    public void sortTasksByDate() {
        TaskComparator taskComparator = new TaskComparator();
        PriorityQueue<Task> priorityQueue = new PriorityQueue<>(taskComparator);
        priorityQueue.addAll(taskList);
        ArrayList<Task> sortedTaskList = new ArrayList<>();
        while (! priorityQueue.isEmpty()) {
            sortedTaskList.add(priorityQueue.poll());
        }
        taskList = sortedTaskList;
    }
}
