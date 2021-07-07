package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulates a task list
 */
public class TaskList {

    /** Task list */
    private final ArrayList<Task> taskArrayList;

    /**
     * Constructor
     */
    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Creates a new deadline and adds it to the task list.
     *
     * @param description Description of the deadline
     * @param isComplete Initial completion status of the deadline
     * @param date Date of the deadline
     * @return Created deadline
     */
    public Deadline addDeadline(String description, boolean isComplete, LocalDate date) {
        assert description != null;
        assert date != null;

        Deadline deadline = new Deadline(description, isComplete, date);
        taskArrayList.add(deadline);
        return deadline;
    }

    /**
     * Creates a new event and adds it to the task list.
     *
     * @param description Description of the event
     * @param isComplete Initial completion status of the event
     * @param date Date of the event
     */
    public Event addEvent(String description, boolean isComplete, LocalDate date) {
        assert description != null;
        assert date != null;

        Event event = new Event(description, isComplete, date);
        taskArrayList.add(event);
        return event;
    }

    /**
     * Adds the task to the task list.
     *
     * @param task
     * @return Task
     */
    public Task addTask(Task task) {
        taskArrayList.add(task);
        return task;
    }

    /**
     * Adds all tasks in another task list to this task list.
     *
     * @param tasks
     */
    public void addTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            addTask(tasks.getTaskAt(i));
        }
    }

    /**
     * Creates a new todo and adds it to the task list.
     *
     * @param description Description of the todo
     * @param isComplete Initial completion status of the todo
     * @return Created todo
     */
    public ToDo addTodo(String description, boolean isComplete) {
        assert description != null;

        ToDo todo = new ToDo(description, isComplete);
        taskArrayList.add(todo);
        return todo;
    }

    /**
     * Marks the task with index specified by taskIndex as complete.
     *
     * @param taskIndex current index of the task
     * @return Completed task if completion is successful, null otherwise
     */
    public Task completeTaskAt(int taskIndex) {
        if (isOutOfRange(taskIndex)) {
            return null;
        }
        Task task = taskArrayList.get(taskIndex);
        task.setComplete();
        return task;
    }

    /**
     * Deletes all tasks in the task list.
     */
    public void deleteAllTasks() {
        taskArrayList.clear();
    }

    /**
     * Deletes the task with index specified by taskIndex.
     *
     * @param taskIndex current index of the task
     * @return Deleted task if deletion is successful, null otherwise
     */
    public Task deleteTaskAt(int taskIndex) {
        if (isOutOfRange(taskIndex)) {
            return null;
        }
        Task task = getTaskAt(taskIndex);
        taskArrayList.remove(taskIndex);
        return task;
    }

    /**
     * Gets the number of tasks in the current task list.
     *
     * @return Number of tasks in the current task list
     */
    int getNumOfTasks() {
        return taskArrayList.size();
    }

    /**
     * Gets the task specified by its index in the task list.
     *
     * @param taskIndex Current index of the task
     * @return Task at specified index if it exists, null otherwise
     */
    Task getTaskAt(int taskIndex) {
        if (isOutOfRange(taskIndex)) {
            return null;
        }
        return taskArrayList.get(taskIndex);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise
     */
    boolean isEmpty() {
        return taskArrayList.size() == 0;
    }

    /**
     * Retrieves a sublist of tasks whose descriptions contain the specified keyword.
     *
     * @param keyword Keyword
     * @return Sublist of tasks
     */
    public Task[] getSublistContainingKeyword(String keyword) {
        assert keyword != null;

        ArrayList<Task> tasksContainingKeyword = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.hasKeyword(keyword)) {
                tasksContainingKeyword.add(task);
            }
        }
        return tasksContainingKeyword.toArray(new Task[0]);
    }

    /**
     * Checks if the task index is within valid range.
     *
     * @param taskIndex
     * @return true if the task index is within valid range, false otherwise
     */
    private boolean isOutOfRange(int taskIndex) {
        return taskIndex < 0 || taskIndex >= taskArrayList.size();
    }
}
