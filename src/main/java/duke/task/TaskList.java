package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.Storage;

import duke.exception.DateParseException;
import duke.exception.InvalidTaskException;
import duke.exception.StorageException;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;

    /**
     * Creates a TaskList.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     */
    private TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Creates and initialises a TaskList.
     * The TaskList is initialised by adding tasks stored in the local storage from previous sessions.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @return The new TaskList created, filled with existing Tasks.
     * @throws StorageException if Task cannot be stored in local storage.
     * @throws DateParseException if Task date (if any) cannot be parsed into LocalDate object.
     */
    public static TaskList initialiseTaskList(Storage storage) throws StorageException, DateParseException {
        TaskList newTaskList = new TaskList(storage);

        try {
            storage.readTaskStorage().forEach(taskString -> {
                String[] t = taskString.split(" [|] ");
                // Tasks are stored in the format: type | isCompleted | taskName | date
                switch (t[0]) {
                case "T":
                    newTaskList.taskList.add(Todo.existingTodo(t[2], t[1].equals("1")));
                    break;
                case "D":
                    newTaskList.taskList.add(Deadline.existingDeadline(t[2], t[1].equals("1"), LocalDate.parse(t[3])));
                    break;
                case "E":
                    newTaskList.taskList.add(Event.existingEvent(t[2], t[1].equals("1"), LocalDate.parse(t[3])));
                    break;
                }
            });
        } catch (DateTimeParseException e) {
            throw new DateParseException("Oops! Please make sure your date is of YYYY-MM-DD format ;A;");
        }

        return newTaskList;
    }

    /**
     * Adds a task of type Todo to the TaskList.
     * @param taskName A string representing the name of the todo.
     * @return The todo added to the TaskList.
     */
    public Task addTask(String taskName) {
        Todo newTodo = Todo.newTodo(taskName);
        this.taskList.add(newTodo);
        return newTodo;
    }

    /**
     * Adds a task of type Deadline or Event to the TaskList.
     * @param type A TaskType representing the type of Task to be added.
     * @param taskName A string representing the name of the task.
     * @param taskDate A string representing the date of the task.
     * @return The task added to the TaskList.
     * @throws InvalidTaskException if task type is neither Deadline nor Event.
     */
    public Task addTask(TaskType type, String taskName, LocalDate taskDate) throws InvalidTaskException {
        switch (type) {
        case EVENT:
            Event newEvent = Event.newEvent(taskName, taskDate);
            this.taskList.add(newEvent);
            return newEvent;
        case DEADLINE:
            Deadline newDeadline = Deadline.newDeadline(taskName, taskDate);
            this.taskList.add(newDeadline);
            return newDeadline;
        }
        throw new InvalidTaskException("Oh dear! I'm not sure what kind of task to add ;A;");
    }

    /**
     * Marks Task as completed.
     * @param index An integer representing the index of the task in the TaskList.
     * @return The task marked as completed.
     * @throws InvalidTaskException if index specified is invalid (does not refer to a task in the TaskList).
     */
    public Task completeTask(int index) throws InvalidTaskException {
        if (index > this.taskList.size() || index <= 0) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }
        return this.taskList.get(index - 1).markAsDone();
    }

    /**
     * Removes a Task from the TaskList.
     * @param index An integer representing the index of the task in the TaskList.
     * @return The removed task.
     * @throws InvalidTaskException if index specified is invalid (does not refer to a task in the TaskList).
     */
    public Task deleteTask(int index) throws InvalidTaskException {
        if (index > this.taskList.size() || index <= 0) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }
        Task task = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        return task;
    }

    /**
     * Gets the list of Tasks.
     * @return An ArrayList containing all Task in the TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the number of Tasks in the TaskList.
     * @return An integer value representing the number of Tasks in the TaskList.
     */
    public int taskListSize() {
        return this.taskList.size();
    }

    /**
     * Gets a String containing values of all Tasks in the TaskList.
     * This String is to be stored in local storage, allowing tasks to persist between sessions.
     * @return A String containing values of all Tasks in the TaskList.
     */
    public String getSaveString() {
        StringBuilder saveString = new StringBuilder();
        for (Task task : this.taskList) {
            saveString.append(task.toSaveString());
        }
        return (saveString.toString());
    }
}
