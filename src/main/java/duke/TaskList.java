package duke;

import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the user's list of tasks.
 */
public class TaskList {

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
     * @throws WrongFormatException If an error is present in the format of a task in the save file.
     */
    public TaskList(List<String> listOfTasks) throws WrongFormatException {
        initiateTaskList(listOfTasks);
    }

    /**
     * Populates the task list with tasks specified by the listOfTasks.
     *
     * @param listOfTasks The list of tasks in String format, to populate the task list with.
     * @throws WrongFormatException If an error is present in the format of a task in the save file.
     */
    private void initiateTaskList(List<String> listOfTasks) throws WrongFormatException {
        for (String s : listOfTasks) {
            String[] splitLine = s.split("\\|");
            switch (splitLine[0]) {
            case "[T]": // To-Do
                tasks.add(new ToDo(splitLine[2], !splitLine[1].equals("0")));
                break;
            case "[E]": // duke.task.Event
                tasks.add(new Event(splitLine[2], splitLine[3], !splitLine[1].equals("0")));
                break;
            case "[D]": // duke.task.Deadline
                tasks.add(new Deadline(splitLine[2], LocalDateTime.parse(splitLine[3]).format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HHmm")), !splitLine[1].equals("0")));
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
    @Override
    public String toString() {
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append("\n").append(index++).append(".").append(task);
        }
        return result.toString();
    }
}
