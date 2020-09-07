package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Stores the user's list of tasks.
 */
public class TaskList {

    private static final String DONE = Integer.toString(Task.DONE_SYMBOL_MEMORY);

    /** The user's list of tasks */
    private List<Task> tasks = new ArrayList<>();

    /**
     * Creates and initializes a list of tasks that is empty.
     */
    public TaskList() {}

    /**
     * Creates and initializes a list of tasks and populates it with tasks specified by the listOfTasks.
     *
     * @param listOfTasks The list of tasks in String format, to populate the task list with.
     */
    public TaskList(List<String> listOfTasks) {
        initiateTaskList(listOfTasks);
    }

    /**
     * Populates the task list with tasks specified by the listOfTasks.
     *
     * @param listOfTasks The list of tasks in String format, to populate the task list with.
     */
    private void initiateTaskList(List<String> listOfTasks) {
        for (String taskString : listOfTasks) {
            String[] taskStringParts = taskString.split("\\|");
            String taskTypeSymbol = taskStringParts[0];
            String doneSymbol = taskStringParts[1];
            String taskDescription = taskStringParts[2];
            switch (taskTypeSymbol) {
            case ToDo.TASK_TYPE_SYMBOL:
                tasks.add(new ToDo(taskDescription, doneSymbol.equals(DONE)));
                break;
            case Event.TASK_TYPE_SYMBOL:
                String eventLocation = taskStringParts[3];
                tasks.add(new Event(taskDescription, eventLocation, doneSymbol.equals(DONE)));
                break;
            case Deadline.TASK_TYPE_SYMBOL:
                String deadline = taskStringParts[3];
                tasks.add(new Deadline(taskDescription, LocalDateTime.parse(deadline).toLocalDate(),
                                LocalDateTime.parse(deadline), doneSymbol.equals(DONE)));
                break;
            default:
                System.err.println("Error in last save. Now loading a new, empty task list.");
                break;
            }
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The task list.
     */
    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the task in the task list that has the index specified by taskIndex.
     *
     * @param taskIndex The index of the task in the task list.
     * @return The task with the specified index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskIndex The index of the task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Indicates if the task list is empty or not.
     *
     * @return true if the task list is empty; false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the String representation of the task list where all the tasks are represented in a top-down
     * sequential order based on their indexes in the list. Each task occupies one line.
     *
     * @return The String representation of the task list.
     */
    public String toStringForGui() {
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append("\n").append(index++).append(".").append(task.toStringForGui());
        }
        return result.toString();
    }

    /**
     * Returns the String representation of the task list where all the tasks are represented in a top-down
     * sequential order based on their indexes in the list. Each task occupies one line.
     *
     * @return The String representation of the task list.
     */
    public String toStringForCli() {
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append("\n").append(index++).append(".").append(task.toStringForCli());
        }
        return result.toString();
    }
}
