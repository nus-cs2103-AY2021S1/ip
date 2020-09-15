package duke;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidTaskTypeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private List<Task> tasks;

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

    /**
     * Retrieves the tasks in TaskList.
     * @return Returns a list of all tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Find tasks matching keyword.
     * @param keyword Keyword of task.
     * @return Returns a list of tasks that matches keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> searchResults =
            tasks.stream()
                 .filter(task -> task.getDescription().contains(keyword))
                 .collect(toList());

        return searchResults;

    }

    /**
     * Adds a new task to TaskList.
     * @param taskDescription Description of task to be added.
     * @param date Date for deadline and event tasks.
     * @param taskType Type of tasks to be added.
     * @throws DukeException If taskType is not defined.
     */
    public void addTask(String taskDescription, String date, TaskType taskType) throws DukeException {
        assert !taskDescription.isEmpty() : "Task should have a description";
        switch (taskType) {
        case TODO:
            tasks.add(new TodoTask(taskDescription));
            break;
        case DEADLINE:
            assert !date.isEmpty() : "Deadline task should have a date.";
            tasks.add(new DeadlineTask(taskDescription, date));
            break;
        case EVENT:
            assert !date.isEmpty() : "Event task should have a date.";
            tasks.add(new EventTask(taskDescription, date));
            break;
        default:
            throw new InvalidTaskTypeException("Invalid Task Type");
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

    /**
     * Retrieves the number of tasks in the task list.
     * @return Returns the number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task in TaskList.
     * @param index Index of task to be retrieved.
     * @return Task
     */
    public Task get(int index) throws InvalidIndexException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
        }
        return tasks.get(index);
    }

    @Override
    public String toString() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
