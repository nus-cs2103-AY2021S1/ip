import java.time.LocalDate;
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
        foundTasks = this.taskList.stream().filter(task -> task.description.equals(keyWord))
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
     * Retrieves task to be updated.
     *
     * @param taskNumber index of the updating tasks.
     * @return the task to be updated.
     */
    protected Task getTaskToUpdate(int taskNumber) {
        assert (taskNumber >= 1) : "invalid task number";
        return this.taskList.get(taskNumber - 1);
    }

    /**
     * Returns the updated version of a task.
     *
     * @param detailToUpdate string containing what to update.
     * @return new updated task.
     * @throws DukeException
     */
    protected Task getUpdatedTask(String detailToUpdate) throws DukeException {
        try {
            Task updatingTask = this.taskList.stream()
                    .filter(task -> task.isBeingUpdated)
                    .findFirst()
                    .get();
            String[] updateDetails = detailToUpdate.split(" ", 2);
            if (updateDetails[0].equals("date")) {
                LocalDate newDate = LocalDate.parse(updateDetails[1]);
                if (updatingTask instanceof Deadline) {
                    return new Deadline(updatingTask.description, newDate);
                } else {
                    return new Event(updatingTask.description, newDate);
                }
            } else if (updateDetails[0].equals("desc")) {
                String newDescription = updateDetails[1];
                if (updatingTask instanceof Deadline) {
                    return new Deadline(newDescription, ((Deadline) updatingTask).getTaskDeadline());
                } else if (updatingTask instanceof Event) {
                    return new Event(newDescription, ((Event) updatingTask).getTaskDeadline());
                } else {
                    return new Todo(newDescription);
                }
            } else {
                throw new InvalidCommandException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Updates the tasklist with the updated task.
     *
     * @param updatedTask the new updated task.
     */
    protected void updateTask(Task updatedTask) {
        this.taskList = this.taskList.stream()
                .map(task -> task.isBeingUpdated ? updatedTask : task)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
