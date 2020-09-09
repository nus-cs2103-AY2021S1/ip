package duke;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;



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
        assert taskList != null;
        return taskList;
    }

    /**
     * Gets the number of tasks the user has.
     * @return the number of tasks
     */
    public int getCount() {
        assert taskList != null;
        return taskList.size();
    }

    /**
     * Marks a particular task as completed and returns it.
     * @param index the task number to be marked as completed
     * @return the completed task
     */
    public Task completeTask(int index) {
        assert taskList != null;
        taskList.get(index - 1).markAsDone();
        return taskList.get(index - 1);
    }

    /**
     * Deletes a particular task and returns it.
     * @param index the task number to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        assert taskList != null;
        return taskList.remove(index - 1);
    }

    /**
     * Adds a todo to the list of tasks and returns it.
     * @param name the name of the todo task
     * @return the newly added todo
     */
    public Task addTodo(String name) {
        assert !name.isBlank();
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
        assert !name.isBlank();
        assert by != null;
        Deadline deadline = new Deadline(name, false, by);
        addTask(deadline);
        return deadline;
    }

    /**
     * Adds an event to the list of tasks and returns it.
     * @param name the name of the event task
     * @param at the date the event is on
     * @return the newly added event
     */
    public Task addEvent(String name, Date at) {
        assert !name.isBlank();
        assert at != null;
        Event event = new Event(name, false, at);
        addTask(event);
        return event;
    }

    /**
     * Adds a task to the list of tasks.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        assert taskList != null;
        taskList.add(task);
    }

    /**
     * Obtains a list of tasks that are due or on a specified date.
     * @param date the queried date
     * @return the list of tasks that are due or on that date
     */
    public List<Task> getTasksWithDate(Date date) {
        assert taskList != null;
        assert date != null;
        List<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getType() != TaskType.TODO && task.getDate().equals(date)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Obtains a list of tasks containing the specific keyword or keywords.
     * @param keyword the keyword queried by user
     * @return the list of tasks with the specific keyword or keywords
     */
    public List<Task> getTasksWithWord(String keyword) {
        assert taskList != null;
        assert ! keyword.isBlank();
        List<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getName().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
}
