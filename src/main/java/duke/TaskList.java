package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskType;
import duke.task.Todo;
import duke.task.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents the current list of tasks the user has.
 * There are operations for the user to manipulate the list of tasks.
 */
public class TaskList {
    private final List<Task> taskList;

    /**
     * Creates an instance of TaskList which stores the current list of tasks.
     * @param taskList the list of tasks the user has
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates an instance of TaskList with no existing tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the current list of tasks the user has.
     * @return the current list of tasks
     */
    public List<Task> getTasks() {
        return taskList;
    }

    /**
     * Gets the number of tasks the user has.
     * @return the number of tasks
     */
    public int getCount() {
        return taskList.size();
    }

    /**
     * Marks a particular task as completed and returns it.
     * @param index the task number to be marked as completed
     * @return the completed task
     * @throws IndexOutOfBoundsException If the task number provided is invalid.
     */
    public Task completeTask(int index) throws IndexOutOfBoundsException {
        taskList.get(index - 1).markAsDone();
        return taskList.get(index - 1);
    }

    /**
     * Deletes a particular task and returns it.
     * @param index the task number to be deleted
     * @return the deleted task
     * @throws IndexOutOfBoundsException If the task number provided is invalid.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return taskList.remove(index - 1);
    }

    /**
     * Adds a todo to the list of tasks and returns it.
     * @param name the name of the todo task
     * @return the newly added todo
     */
    public Task addTodo(String name) {
        Todo todo = new Todo(name, false);
        addTask(todo);
        return todo;
    }

    /**
     * Adds a deadline to the list of tasks and returns it.
     * @param name the name of the deadline task
     * @param by the date the deadline is due
     * @return the newly added deadline
     */
    public Task addDeadline(String name, Date by) {
        Deadline deadline = new Deadline(name, false, by);
        addTask(deadline);
        return deadline;
    }

    /**
     * Adds a event to the list of tasks and returns it.
     * @param name the name of the event task
     * @param at the date the event is on
     * @return the newly added event
     */
    public Task addEvent(String name, Date at) {
        Event event = new Event(name, false, at);
        addTask(event);
        return event;
    }

    /**
     * Adds a task to the list of tasks.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Obtains a list of tasks that are due or on a specified date.
     * @param date the queried date
     * @return the list of tasks that are due or on that date
     */
    public List<Task> getTasksWithDate(Date date) {
        List<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getType() != TaskType.TODO && task.getDate().equals(date)) {
                result.add(task);
            }
        }
        return result;
    }
}
