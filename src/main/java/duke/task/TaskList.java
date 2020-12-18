package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TaskList class is a list of {@link Task}s.
 * It supports the basic operations of manipulating tasks and answering queries.
 */
public class TaskList {
    private static final String COMPLETED_TASKS_MESSAGE = "Here are the task(s) that have been completed: \n";
    private static final String ALL_TASKS_COMPLETED_MESSAGE = "Congratulations! You have completed all your tasks!";
    private static final String MATCHING_TASKS_MESSAGE = "Here are the task(s) containing keyword(s): ";
    private static final String NO_TASKS_COMPLETED_MESSAGE = "☹ OOPS!!! You have not completed any task yet.";
    private static final String NO_MATCHING_TASKS_MESSAGE = "There are no tasks containing keyword: ";
    private static final String PENDING_TASKS_MESSAGE = "Here are the task(s) that are pending: \n";
    private static final String SHOW_TASKS_MESSAGE = "Here are the task(s)/deadline(s) happening on: ";
    private static final String NO_TASKS_ON_DATE_MESSAGE = "There are no task/deadline happening on: ";
    private List<Task> tasks;

    /**
     * Instantiates an empty list of {@link Task}s.
     *
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Instantiates a new TaskList with a provided list of {@link Task}s.
     *
     * @param tasks A list of {@link Task}s.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a {@link Task} to the end of the list.
     *
     * @param task A task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a {@link Task} at a certain index (starting from 1) as completed.
     *
     * @param index The index of the task to be marked as completed.
     */
    public boolean markDone(int index) {
        // Asserts that the index should not be out of bound.
        assert index <= tasks.size();
        if (tasks.get(index - 1).isTaskDone()) {
            return false;
        }
        tasks.get(index - 1).markAsDone();
        return true;
    }

    /**
     * Returns a {@link Task} in the list based on the given index.
     *
     * @param index Index of the task in the list.
     * @return The task at the provided index.
     */
    public Task getTask(int index) {
        // Asserts that the index should not be out of bound.
        assert index <= tasks.size();
        return tasks.get(index - 1);
    }

    /**
     * Returns the current number of {@link Task}s in the list.
     *
     * @return The current size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of all the {@link Task}s in the current list.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns all the {@link Task}s formatted properly.
     *
     * @return A formatted String of all the {@link Task}s.
     */
    public String listTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1)
                    .append(".")
                    .append(tasks.get(i))
                    .append(i == tasks.size() - 1 ? "" : "\n");
        }
        return output.toString();
    }

    /**
     * Returns all the {@link Task}s that happen on or due at a specific date.
     *
     * @param date The date to search for.
     * @return A formatted String of all the tasks that happen on or due at the provided date.
     */
    public String showTasksOnDate(LocalDate date) {
        // Asserts that a date object is passed in.
        assert date != null;
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getDate() != null && task.getDate().equals(date)).collect(Collectors.toList());
        if (filteredTasks.size() == 0) {
            return NO_TASKS_ON_DATE_MESSAGE
                    + date.format(DateTimeFormatter.ofPattern("MMMM d yyyy"));
        }
        StringBuilder output = new StringBuilder();
        output.append(SHOW_TASKS_MESSAGE).append(date).append("\n");

        for (int i = 0; i < filteredTasks.size(); i++) {
            output.append(String.format("%d. %s" + (i == filteredTasks.size() - 1 ? "" : "\n"),
                    i + 1, filteredTasks.get(i)));
        }

        return output.toString();
    }

    /**
     * Returns all the {@link Task}s that have not been completed.
     *
     * @return A formatted String of all pending {@link Task}s.
     */
    public String showPendingTasks() {
        List<Task> pendingTasks = tasks.stream()
                .filter(task -> !task.isTaskDone()).collect(Collectors.toList());
        return getFilteredTasksString(pendingTasks, ALL_TASKS_COMPLETED_MESSAGE, PENDING_TASKS_MESSAGE);
    }

    /**
     * Returns all the {@link Task}s that have been completed.
     *
     * @return A formatted String of all completed {@link Task}s.
     */
    public String showCompletedTasks() {
        List<Task> completedTasks = tasks.stream().filter(Task::isTaskDone).collect(Collectors.toList());
        return getFilteredTasksString(completedTasks, NO_TASKS_COMPLETED_MESSAGE, COMPLETED_TASKS_MESSAGE);
    }

    private String getFilteredTasksString(List<Task> filteredTasks, String noTasksMessage, String returnMessage) {
        if (filteredTasks.size() == 0) {
            return noTasksMessage;
        }
        StringBuilder output = new StringBuilder();
        output.append(returnMessage);

        for (int i = 0; i < filteredTasks.size(); i++) {
            output.append(String.format("%d. %s" + (i == filteredTasks.size() - 1 ? "" : "\n"),
                    i + 1, filteredTasks.get(i)));
        }

        return output.toString();
    }

    /**
     * Returns all the {@link Task}s that contains the keyword.
     *
     * @param keywords A variable number of Strings to search for.
     * @return A formatted String of {@link Task}s with description that includes the given keywords.
     */
    public String showMatchingTasks(String... keywords) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.includeKeywords(keywords)).collect(Collectors.toList());
        if (matchingTasks.size() == 0) {
            return NO_MATCHING_TASKS_MESSAGE + Arrays.toString(keywords);
        }
        StringBuilder output = new StringBuilder();
        output.append(MATCHING_TASKS_MESSAGE).append(Arrays.toString(keywords)).append("\n");

        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append(String.format("%d. %s" + (i == matchingTasks.size() - 1 ? "" : "\n"),
                    i + 1, matchingTasks.get(i)));
        }

        return output.toString();
    }

    /**
     * Delete a {@link Task} at a given index.
     *
     * @param index The index of the {@link Task} to be deleted.
     */
    public void deleteTask(int index) {
        // Asserts that the index should not be out of bound.
        assert index <= tasks.size();
        tasks.remove(index - 1);
    }
}
