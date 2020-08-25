package duck.task;

import duck.exception.DuckException;


import java.io.Serializable;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class to handle all operations regarding the current list of tasks
 * by the user.
 * Implements Serializable to be easily stored in a file.
 */
public class TaskList implements Serializable {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Loops through the tasks and extracts their statuses,
     * while prepending their index in the list.
     *
     * @return String array of statuses.
     */
    public String[] getStatuses() {
        String[] statuses = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            statuses[i] = "" + (i + 1) + ". " + this.tasks.get(i).getStatus();
        }
        return statuses;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Checks the index to ensure that it is correct before
     * marking the Task as done.
     * Returns the Task recently marked done to display to user.
     *
     * @param index Index of the task to be marked as done.
     * @return Task that is marked done.
     * @throws DuckException If invalid index is given.
     */
    public Task markDone(int index) throws DuckException {
        if (index > this.tasks.size() || index < 1) {
            throw new DuckException("No such task with that number!");
        } else {
            Task task = this.tasks.get(index - 1);
            task.markDone();
            return task;
        }
    }

    /**
     * Checks the index to ensure that it is correct before
     * deleting the Task
     * Returns the Task recently deleted to display to user.
     *
     * @param index Index of the task to be deleted.
     * @return Task that is deleted.
     * @throws DuckException If invalid index is given.
     */
    public Task deleteTask(int index) throws DuckException {
        if (index > this.tasks.size() || index < 1) {
            throw new DuckException("No such task with that number!");
        } else {
            Task task = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            return task;
        }
    }

    /**
     * Filters the current list of tasks for tasks that have a date.
     * The tasks are then sorted by due date.
     * Optionally sets a maximum date where all tasks with due dates
     * earlier than the maximum date will be returned.
     *
     * @param optionalDate Maximum date to query for tasks.
     * @return String array of statuses of tasks matching the filters.
     */
    public String[] getStatusesByDate(Optional<LocalDate> optionalDate) {

        return this.tasks.stream()
                .filter(TaskWithDate.class::isInstance)
                .map(TaskWithDate.class::cast)
                .sorted((task1, task2) -> task1.getDate().compareTo(task2.getDate()))
                .filter((task) -> {
                    if (optionalDate.isPresent()) {
                        return task.getDate().isBefore(optionalDate.get());
                    } else {
                        return true;
                    }

                })
                .map((task) -> task.getStatus())
                .toArray(String[]::new);
    }
}
