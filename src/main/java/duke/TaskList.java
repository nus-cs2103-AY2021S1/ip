package duke;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    List<Task> tasks;

    /**
     * Constructs a new instance of TaskList with attributes defined in parameters.
     * @param tasks List of tasks in local storage file.
     */
    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new instance of TaskList with an empty TaskList.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a new task to TaskList.
     * @param task Description of task to be added.
     * @param date Date for deadline and event tasks.
     * @param taskType Type of tasks to be added.
     * @throws DukeException If taskType is not defined.
     */
    public void addTask(String task, String date, TaskType taskType) throws DukeException {

        Task t;
        switch (taskType) {
        case TODO:
            t = new TodoTask(task);
            tasks.add(t);
            break;
        case DEADLINE:
            t = new DeadlineTask(task, date);
            tasks.add(t);
            break;
        case EVENT:
            t = new EventTask(task, date);
            tasks.add(t);
            break;
        default:
            throw new DukeException("Invalid Task Type");
        }
    }

    /**
     * Deletes a task in TaskList.
     * @param index Index of task to be deleted.
     * @throws InvalidIndexException If index < 1 or index >= tasks.size().
     */
    public void deleteTask(int index) throws InvalidIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
        }
        tasks.remove(index - 1);
    }

    /**
     * Marks a task in TaskList as done.
     * @param index Index of task to be marked as done.
     * @throws InvalidIndexException If index < 1 or index >= tasks.size().
     */
    public void completeTask(int index) throws InvalidIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
        }
        Task completedTask = tasks.get(index - 1);
        completedTask.markAsDone();
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task in TaskList.
     * @param index Index of task to be retrieved.
     * @return Task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    @Override
    public String toString() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
