package nite.task;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nite.exception.NiteException;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList already containing Tasks.
     * This is used if Storage is able to load data from the text file.
     *
     * @param taskStrings List of lines in the text file, representing Tasks.
     */
    public TaskList(ArrayList<String> taskStrings) {
        tasks = new ArrayList<>();
        for (String taskString : taskStrings) {
            try {
                this.tasks.add(dataToTask(taskString));
            } catch (NiteException ignored) {
                // Line in data which cannot be converted to Task is ignored.
            }
        }
    }

    /**
     * Converts a line of text into a Task.
     *
     * @param taskString Line of text from text file.
     * @return Task which is specified by the line of text
     */
    public Task dataToTask(String taskString) throws NiteException {
        String[] taskLine = taskString.split("~");
        Task task = null;
        switch (taskLine[0]) {
        case "T":
            task = new ToDo(taskLine[2]);
            break;
        case "D":
            task = new Deadline(taskLine[2], taskLine[3]);
            break;
        case "E":
            task = new Event(taskLine[2], taskLine[3]);
            break;
        default:
            assert false : "Cases should be exhaustive.";
            break;
        }
        if (taskLine[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns a String listing out all the tasks in the tasklist.
     *
     * @return String representation of list of tasks.
     */
    public String listTasks() {
        AtomicInteger counter = new AtomicInteger(1);
        Stream<String> stringsStream = tasks.stream().map(t -> String.format("  %d.%s%n",
                counter.getAndIncrement(), t));
        return stringsStream.reduce("", (s, t) -> s += String.format("  %s", t));
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task should not be null.";
        tasks.add(task);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Number of items in TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Converts the TaskList into lines of text.
     *
     * @return List containing lines of text.
     */
    public ArrayList<String> tasksToText() {
        return tasks.stream().map(Task::toData)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a String listing out all the tasks that match the keyword.
     *
     * @return String representation of list of tasks.
     */
    public String findTasks(String keyword) {
        assert !keyword.isEmpty() : "Keyword should not be empty.";
        AtomicInteger counter = new AtomicInteger(1);
        Stream<Task> matchingTasks = tasks.stream().filter(t -> t.hasKeyword(keyword));
        Stream<String> stringsStream = matchingTasks.map(t -> String.format("  %d.%s%n",
                counter.getAndIncrement(), t));
        return stringsStream.reduce("", (s, t) -> s += String.format("  %s", t));
    }

    /**
     * Deletes and returns the task at a position in the list.
     *
     * @param taskNumber Position of the Task in the list of tasks.
     * @return Task which was deleted.
     */
    public Task remove(int taskNumber) {
        assert (taskNumber > 0 && taskNumber < size()) : "Task number should be within bounds";
        Task t = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return t;
    }

    /**
     * Marks a task in the list as done.
     *
     * @param taskNumber Position of Task in the list of tasks.
     * @return Task which was marked as done.
     */
    public Task markTask(int taskNumber) {
        assert (taskNumber > 0 && taskNumber < size()) : "Task number should be within bounds";
        Task t = tasks.get(taskNumber - 1);
        t.markAsDone();
        return t;
    }

    /**
     * Returns a sorted list of tasks based on timing.
     *
     * @param type Type of task, either todo, deadline, or event.
     * @return A sorted ArrayList of tasks.
     */
    public ArrayList<Task> sortedTasks(String type) {
        assert !type.isEmpty() : "Task type cannot be empty.";
        Stream<Task> tasksOfType = tasks.stream().filter(t -> t.typeOfTask().equals(type));
        Stream<Task> sortedTasks = tasksOfType.sorted(new TaskComparator());
        return sortedTasks.collect(Collectors.toCollection(ArrayList::new));
    }
}
