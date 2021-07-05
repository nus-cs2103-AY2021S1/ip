package data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.exception.DukeInvalidUserInputException;

/**
 * Represents an array list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task into the list and then generates and prints a success message.
     * @param task to be added into the list.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Adds a task into the list only.
     * Used when loading data from a storage file.
     * @param task to be added into the list.
     */
    public void load(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a specific task based on the task's index in the task array list
     * and then generates and prints a success message.
     * @param i index of task to be deleted.
     */
    public Task delete(int i) {
        assert i >= 0; //Index of arrays cannot be less than zero.
        Task task = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        return task;
    }

    /**
     * Marks a specific task as done based on task's index in the task array list
     * and then generates and prints a success message.
     * @param i index of task to be marked.
     */
    public Task markDone(int i) {
        assert i >= 0; //Index of arrays cannot be less than zero.
        Task task = this.taskList.get(i - 1);
        task.markAsDone();
        return task;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int i) {
        assert i >= 0; //Index of arrays cannot be less than zero.
        return this.taskList.get(i);
    }

    public int getTotalTask() {
        return this.taskList.size();
    }

    /**
     * Finds tasks that contain the input keyword within their descriptions.
     * @param keyword to find.
     * @return an array list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasksKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.containsKeyword(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Finds tasks that contain the input date within their dates.
     * @param date to find.
     * @return an array list of tasks that contain the date.
     */
    public ArrayList<Task> findTasksDate(String date) throws DukeInvalidUserInputException {
        ArrayList<Task> result = new ArrayList<>();
        try {
            LocalDate inputDate = LocalDate.parse(date);
            assert inputDate != null; //To ensure inputDate has been correctly parsed.
            for (Task task : this.taskList) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.getDate().equals(inputDate)) {
                        result.add(task);
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.getDate().equals(inputDate)) {
                        result.add(task);
                    }
                }
            }
            return result;
        } catch (DateTimeParseException e) {
            throw new DukeInvalidUserInputException("It seems you have entered an invalid date and time. "
                    + "The format should be as follows YYYY-MM-DD hhmm.");
        }
    }

    /**
     * Sorts the current task list lexicographically.
     */
    public void sortTaskListAlphabetically() {
        Collections.sort(this.taskList, new Comparator<Task>() {
            @Override
            public int compare(Task firstTask, Task secondTask) {
                return firstTask.getDescription().compareTo(secondTask.getDescription());
            }
        });
    }

    /**
     * Sorts the current task list by date and time.
     */
    public void sortTaskListDateTime() {
        ArrayList<Task> todoTasks = new ArrayList<>();
        ArrayList<Task> dateTimeTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task instanceof ToDo) {
                todoTasks.add(task);
            } else {
                dateTimeTasks.add(task);
            }
        }
        sortDateTimeTasks(dateTimeTasks);
        dateTimeTasks.addAll(todoTasks);
        this.taskList = dateTimeTasks;
    }

    /**
     * Sorts an arraylist of tasks based on date and time.
     * @param tasks arraylist to be sorted.
     */
    private void sortDateTimeTasks(ArrayList<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task firstTask, Task secondTask) {
                LocalDate firstDate;
                LocalDate secondDate;
                LocalTime firstTime;
                LocalTime secondTime;
                if (firstTask instanceof Deadline) {
                    Deadline firstDeadline = (Deadline) firstTask;
                    firstDate = firstDeadline.getDate();
                    firstTime = firstDeadline.getTime();
                } else {
                    Event firstEvent = (Event) firstTask;
                    firstDate = firstEvent.getDate();
                    firstTime = firstEvent.getStartTime();
                }
                if (secondTask instanceof Deadline) {
                    Deadline secondDeadline = (Deadline) secondTask;
                    secondDate = secondDeadline.getDate();
                    secondTime = secondDeadline.getTime();
                } else {
                    Event secondEvent = (Event) secondTask;
                    secondDate = secondEvent.getDate();
                    secondTime = secondEvent.getStartTime();
                }

                if (firstDate.compareTo(secondDate) == 0) {
                    return firstTime.compareTo(secondTime);
                } else {
                    return firstDate.compareTo(secondDate);
                }
            }
        });
    }
}
