package main.java.duke;

import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulates a task list
 */
public class TaskList {

    /** Task list */
    final ArrayList<Task> taskArrayList;

    /**
     * Constructor
     */
    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Creates a new deadline and adds it to the task list
     *
     * @param description Description of the deadline
     * @param isComplete Initial completion status of the deadline
     * @param date Date of the deadline
     * @return Created deadline
     */
    public Deadline addDeadline(String description, boolean isComplete, LocalDate date) {
        Deadline deadline = new Deadline(description, isComplete, date);
        this.taskArrayList.add(deadline);
        return deadline;
    }

    /**
     * Creates a new event and adds it to the task list
     *
     * @param description Description of the event
     * @param isComplete Initial completion status of the event
     * @param date Date of the event
     */
    public Event addEvent(String description, boolean isComplete, LocalDate date) {
        Event event = new Event(description, isComplete, date);
        this.taskArrayList.add(event);
        return event;
    }

    /**
     * Creates a new todo and adds it to the task list
     *
     * @param description Description of the todo
     * @param isComplete Initial completion status of the todo
     * @return Created todo
     */
    public ToDo addTodo(String description, boolean isComplete) {
        ToDo todo = new ToDo(description, isComplete);
        this.taskArrayList.add(todo);
        return todo;
    }

    /**
     * Marks the task with index specified by taskIndex as complete
     *
     * @param taskIndex current index of the task
     * @return Completed task if completion is successful, null otherwise
     */
    public Task completeTaskAt(int taskIndex) {
        // Check if task index is within valid range
        if (taskIndex >= 0 && taskIndex < this.taskArrayList.size()) {
            Task task = this.taskArrayList.get(taskIndex);
            task.setComplete();
            return task;
        } else {
            return null;
        }
    }

    /**
     * Deletes the task with index specified by taskIndex
     *
     * @param taskIndex current index of the task
     * @return Deleted task if deletion is successful, null otherwise
     */
    public Task deleteTaskAt(int taskIndex) {
        // Check if task index is within valid range
        if (taskIndex >= 0 && taskIndex < this.taskArrayList.size()) {
            Task task = this.getTaskAt(taskIndex);
            this.taskArrayList.remove(taskIndex);
            return task;
        } else {
            return null;
        }
    }

    /**
     * Gets the number of tasks in the current task list
     *
     * @return Number of tasks in the current task list
     */
    int getNumOfTasks() {
        return this.taskArrayList.size();
    }

    /**
     * Gets the task specified by its index in the task list
     *
     * @param taskIndex Current index of the task
     * @return Task at specified index if it exists, null otherwise
     */
    Task getTaskAt(int taskIndex) {
        // Check if task index is within valid range
        if (taskIndex >= 0 && taskIndex < this.getNumOfTasks()) {
            return this.taskArrayList.get(taskIndex);
        } else {
            return null;
        }
    }

    /**
     * Checks if the task list is empty
     *
     * @return true if the task list is empty, false otherwise
     */
    boolean isEmpty() {
        return taskArrayList.size() == 0;
    }
}
