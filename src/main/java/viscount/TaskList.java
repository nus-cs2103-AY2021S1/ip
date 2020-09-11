package viscount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.exception.ViscountUnsupportedOperationException;
import viscount.task.Task;

/**
 * Represents Viscount's task list.
 *
 * Stores the task list and handles operations modifying it.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to task list.
     *
     * @param task Task added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks task as done by its index in the list.
     *
     * @param taskIndex Index of task marked.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size.
     */
    public void markDone(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            Task task = tasks.get(taskIndex);
            task.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }

    /**
     * Marks all not done tasks as done and returns a list of these tasks.
     *
     * @return List of previously not done tasks.
     */
    public List<Task> markAndGetAllNotDoneTasks() {
        List<Task> notDoneTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (!task.getIsDone()) {
                notDoneTasks.add(task);
                task.setDone(true);
            }
        }

        return notDoneTasks;
    }

    /**
     * Edit the description of a task by its index.
     *
     * @param taskIndex Index of task edited.
     * @param newDescription New description of the task.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size.
     */
    public void editTaskDescription(int taskIndex, String newDescription) throws ViscountIndexOutOfBoundsException {
        try {
            Task task = tasks.get(taskIndex);
            task.setDescription(newDescription);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }

    /**
     * Edit the date time of a deadline or event by its index.
     *
     * @param taskIndex Index of task edited.
     * @param newDateTime New date time of the deadline or event.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size.
     * @throws ViscountUnsupportedOperationException If the task edited is a todo.
     */
    public void editTaskDateTime(int taskIndex, LocalDateTime newDateTime)
            throws ViscountIndexOutOfBoundsException, ViscountUnsupportedOperationException {
        try {
            Task task = tasks.get(taskIndex);
            task.setDateTime(newDateTime);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        } catch (UnsupportedOperationException e) {
            throw new ViscountUnsupportedOperationException("edit todo date time");
        }
    }

    /**
     * Removes task from list by its index in the list.
     *
     * @param taskIndex Index of task removed.
     * @return Task removed.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size
     */
    public Task remove(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            return tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }

    /**
     * Deletes all tasks and returns the task list before all tasks were deleted.
     *
     * @return Task list before all tasks were deleted.
     */
    public List<Task> deleteAndGetAllTasks() {
        List<Task> copyOfTasks = new ArrayList<>(tasks);

        tasks = new ArrayList<>();

        return copyOfTasks;
    }

    /**
     * Deletes all done tasks and returns a list of all the deleted tasks that were done.
     *
     * @return List of all the deleted tasks that were done.
     */
    public List<Task> deleteAndGetAllDoneTasks() {
        List<Task> doneTasks = tasks.stream()
                .filter(Task::getIsDone)
                .collect(Collectors.toList());

        tasks.removeAll(doneTasks);

        return doneTasks;
    }

    /**
     * Gets task list.
     *
     * @return Task list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets task by its index in the list.
     *
     * @param taskIndex Index of task.
     * @return Task with index taskIndex.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size
     */
    public Task getTask(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            return tasks.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }

    /**
     * Gets size of task list.
     *
     * @return Size of task list.
     */
    public int getTasksSize() {
        return tasks.size();
    }
}
