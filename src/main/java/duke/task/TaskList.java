package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.Storage;
import duke.exception.DuplicateTaskException;
import duke.exception.InvalidTaskException;

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
     */
    public static TaskList initialiseTaskList(Storage storage) {
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
                default:
                    assert false : "Invalid object stored in local storage";
                }
            });
        } catch (DateTimeParseException e) {
            assert false : "Invalid date stored in local storage";
        }

        return newTaskList;
    }

    /**
     * Adds a task of type Todo to the TaskList.
     * @param taskName A string representing the name of the todo.
     * @return The todo added to the TaskList.
     */
    public Task addTask(String taskName) throws DuplicateTaskException {
        Todo newTodo = Todo.newTodo(taskName);
        checkForDuplicates(newTodo);
        this.taskList.add(newTodo);
        return newTodo;
    }

    /**
     * Adds a task of type Deadline or Event to the TaskList.
     * @param type A TaskType representing the type of Task to be added.
     * @param taskName A string representing the name of the task.
     * @param taskDate A string representing the date of the task.
     * @return The task added to the TaskList.
     */
    public Task addTask(TaskType type, String taskName, LocalDate taskDate) throws DuplicateTaskException {
        Task newTask;
        switch (type) {
        case EVENT:
            newTask = Event.newEvent(taskName, taskDate);
            break;
        case DEADLINE:
            newTask = Deadline.newDeadline(taskName, taskDate);
            break;
        default:
            throw new AssertionError("Invalid TaskType specified");
        }

        checkForDuplicates(newTask);
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Checks if the task newTask is a duplicated of any previously created tasks.
     * @param newTask The new task to be added to the taskList.
     * @throws DuplicateTaskException If the task has previously been created.
     */
    public void checkForDuplicates(Task newTask) throws DuplicateTaskException {
        for (Task task : this.taskList) {
            if (newTask.isEqualTo(task)) {
                throw new DuplicateTaskException(task.toString());
            }
        }
    }

    /**
     * Marks Task as completed.
     * @param index An integer representing the index of the task in the TaskList.
     * @return The task marked as completed.
     * @throws InvalidTaskException If index specified is invalid (does not refer to a task in the TaskList).
     */
    public Task markTaskAsDone(int index) throws InvalidTaskException {
        if (index > this.taskList.size() || index <= 0) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }
        return this.taskList.get(index - 1).markAsDone();
    }

    /**
     * Removes a Task from the TaskList.
     * @param index An integer representing the index of the task in the TaskList.
     * @return The removed task.
     * @throws InvalidTaskException If index specified is invalid (does not refer to a task in the TaskList).
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
     * Finds tasks that have date set as today.
     * @return An ArrayList containing all Tasks that have date set as today.
     */
    public ArrayList<Task> getTasksToday() {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.isToday()) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    /**
     * Finds tasks that match the search term.
     * @param matchString A string representing the search term.
     * @return An ArrayList containing all Tasks that match the search term provided.
     */
    public ArrayList<Task> findTasks(String matchString) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getTaskName().contains(matchString)) {
                filteredList.add(task);
            }
        }
        return filteredList;
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
    public int getTaskListSize() {
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
