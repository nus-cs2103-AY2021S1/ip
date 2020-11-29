import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class to represent all the tasks.
 * @author vanGoghhh
 */

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for tasklist.
     *
     * @param taskList arraylist containing all the tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Mark a task as completed.
     *
     * @param completedTask task to be completed.
     */
    protected void markTaskDone(Task completedTask) {
        int index = taskList.indexOf(completedTask);
        completedTask.markAsDone();
        this.taskList.set(index, completedTask);
    }

    /**
     * Adds a task into the tasklist.
     *
     * @param task the task to be added.
     */
    protected void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param task the task to be deleted.
     */
    protected void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    /**
     * .
     * Gets the tasklist containing all the tasks.
     *
     * @return arraylist containing all the tasks.
     */
    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns number of uncompleted tasks in the tasklist.
     *
     * @return number of uncompleted tasks.
     */
    protected long checkTasksLeft() {
        long sum = taskList.stream().filter(task -> !task.getStatus())
                .count();

        assert (sum >= 0) : "Invalid number of tasks";
        return sum;
    }

    /**
     * Search and return tasks using keyword.
     *
     * @param keyWord word used to search.
     * @return Tasks with description matching the keyword.
     */
    protected ArrayList<Task> findTask(String keyWord) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        foundTasks = this.taskList.stream().filter(task -> task.getDescription().equals(keyWord))
                .collect(Collectors.toCollection(ArrayList::new));
        return foundTasks;
    }

    /**
     * Retrieve a tasklist with only events and deadlines.
     *
     * @return ArrayList containing only events and deadlines.
     */
    protected ArrayList<Task> filterTask() {
        return this.taskList.stream().filter(task -> task instanceof Deadline ||
                task instanceof Event).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sorts tasks in tasklist in chronological order.
     *
     * @return ArrayList of tasks sorted in chronological order.
     */
    protected ArrayList<Task> sortTask() {
        ArrayList<Task> sortedEventAndDeadline = this.taskList.stream()
                .filter(task -> task instanceof Deadline
                        || task instanceof Event)
                .sorted((task1, task2) -> task1.getTaskDeadline()
                        .compareTo(task2.getTaskDeadline()))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Task> onlyTodo = this.taskList.stream()
                .filter(task -> task instanceof Todo)
                .collect(Collectors.toCollection(ArrayList::new));
        sortedEventAndDeadline.addAll(onlyTodo);
        return sortedEventAndDeadline;

    }

    /**
     * Updates existing task with new task.
     * @param updatingTask task to be updated.
     * @param updateDetails new details to update the task with.
     * @param taskIndex index of task in the task list.
     * @return new updated task.
     * @throws DukeException
     */
    protected Task updateTask(Task updatingTask, String updateDetails, int taskIndex) throws DukeException {
        try {
            String[] updatingDetails = updateDetails.split(" ", 2);
            String updateType = updatingDetails[0];
            Task updatedTask;
            if (updateType.equals("desc")) {
                updatedTask = updatingTask.updateTaskDescription(updatingDetails[1]);
            } else if (updateType.equals("date")) {
                if (updatingTask instanceof TimedTask) {
                    LocalDate newDueDate = LocalDate.parse(updatingDetails[1]);
                    updatedTask = updatingTask.updateTimedTaskDeadline(newDueDate);
                } else {
                    throw new InvalidCommandException();
                }
            } else {
                throw new InvalidCommandException();
            }
            taskList.set(taskIndex - 1, updatedTask);
            return updatedTask;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new InvalidCommandException();
        }
    }
}
