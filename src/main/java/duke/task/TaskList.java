package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Encapsulates an ArrayList of Tasks.
 */
public class TaskList {
    private int nextIndex = 1;
    private Map<Integer, Task> tasksMap = new HashMap<>();

    /**
     * Adds the given task to the list.
     *
     * @param task Task to be added.
     * @return The given task.
     */
    public Task addTask(Task task) {
        tasksMap.put(nextIndex, task);
        nextIndex++;
        return task;
    }

    /**
     * Adds a Todo task.
     *
     * @param args String representing a Todo task's description.
     * @return The new Todo instance.
     * @throws TaskException If the task cannot be created.
     */
    public Task addTodo(String args) throws TaskException {
        Todo todo = new Todo(args);
        tasksMap.put(nextIndex, todo);
        nextIndex++;
        return todo;
    }

    /**
     * Adds a Deadline task.
     *
     * @param args String with parameters joined as specified in Deadline.
     * @return The new Deadline instance.
     * @throws TaskException If the task cannot be created.
     */
    public Task addDeadline(String args) throws TaskException {
        try {
            Deadline deadline = Deadline.create(args);
            tasksMap.put(nextIndex, deadline);
            nextIndex++;
            return deadline;
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format");
        }
    }

    /**
     * Adds an Event.
     *
     * @param args String with parameters joined as specified in Event.
     * @return The new Event instance.
     * @throws TaskException If the task cannot be created.
     */
    public Task addEvent(String args) throws TaskException {
        try {
            Event event = Event.create(args);
            tasksMap.put(nextIndex, event);
            nextIndex++;
            return event;
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format");
        }
    }

    /**
     * Completes tasks at the given indexes.
     * The given indexes should be as given in the list command.
     *
     * @param indexes An array of indexes whose tasks should be completed.
     * @return Tasks that were completed.
     * @throws InvalidTaskIndexException If any one index is invalid.
     */
    public List<Task> completeTasks(String... indexes) throws InvalidTaskIndexException {
        Set<Integer> intIndexes = verifyIndexes(indexes);
        List<Task> tasks = new ArrayList<>();
        for (int taskIndex : intIndexes) {
            Task task = tasksMap.get(taskIndex);
            task.completeTask();
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Deletes tasks at the given indexes.
     * The given indexes should be as given in the list command.
     *
     * @param indexes An array of indexes whose tasks should be deleted.
     * @return Tasks that were deleted.
     * @throws InvalidTaskIndexException If any one index is invalid.
     */
    public List<Task> deleteTask(String... indexes) throws InvalidTaskIndexException {
        Set<Integer> intIndexes = verifyIndexes(indexes);
        List<Task> tasks = new ArrayList<>();
        for (int taskIndex : intIndexes) {
            Task task = tasksMap.remove(taskIndex);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Verifies if the given list of indexes are valid and within the taskList range.
     *
     * @param indexes List of indexes to verify. Each index should be based on the list command.
     * @return List of tasks corresponding to the list in the list command.
     * @throws InvalidTaskIndexException If the given list contains one or more invalid indexes.
     */
    private Set<Integer> verifyIndexes(String... indexes) throws InvalidTaskIndexException {
        Set<Integer> taskIndexes = new HashSet<>();
        for (String index : indexes) {
            try {
                int taskIndex = Integer.parseInt(index);
                if (!tasksMap.containsKey(taskIndex)) {
                    throw new InvalidTaskIndexException("One or more indexes is not valid. "
                            + "Please check the list again.");
                }
                taskIndexes.add(taskIndex);
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException("One or more indexes is not valid. "
                        + "Please check the list again.");
            }
        }
        return taskIndexes;
    }

    /**
     * Searches the taskList for tasks whose description contains the searchTerm.
     *
     * @param searchTerm Search term to search with.
     * @return List of tasks whose description has the search term.
     */
    public List<Task> findTasks(String searchTerm) {
        List<Task> results = new ArrayList<>();
        for (int key : tasksMap.keySet()) {
            Task task = tasksMap.get(key);
            if (task.hasSearchTerm(searchTerm)) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     * Returns a string that represents all the tasks.
     */
    public String getStorageDocument() throws TaskTypeDecodeException {
        StringBuilder string = new StringBuilder();
        boolean isFirst = true;
        for (int key : tasksMap.keySet()) {
            Task task = tasksMap.get(key);
            String taskStorageString = TaskType.appendIdentifier(task.toStorageString(), task.getTaskType());
            if (isFirst) {
                string.append(taskStorageString);
                isFirst = false;
            } else {
                string.append("\n").append(taskStorageString);
            }
        }
        return string.toString();
    }

    /**
     * Creates an task by reading a storage line.
     *
     * @param line The storage line to interpret.
     */
    public void parseStorageLine(String line) throws TaskTypeDecodeException, TaskException {
        TaskType taskType = TaskType.decodeTaskType(line);
        String taskStorageLine = TaskType.getStorageLine(line, taskType);
        switch (taskType) {
        case DEADLINE:
            addTask(Deadline.parseStorageString(taskStorageLine));
            break;
        case EVENT:
            addTask(Event.parseStorageString(taskStorageLine));
            break;
        case TODO:
            addTask(Todo.parseStorageString(taskStorageLine));
            break;
        default:
            break;
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The tasks in a string format.
     */
    public String getTaskList() {
        StringBuilder string = new StringBuilder();
        boolean isFirst = true;
        for (int i = 1; i < nextIndex; i++) {
            boolean taskExists = tasksMap.containsKey(i);
            if (isFirst && taskExists) {
                string.append(i).append(": ").append(tasksMap.get(i).toString());
                isFirst = false;
            } else if (taskExists) {
                string.append("\n").append(i).append(": ").append(tasksMap.get(i).toString());
            }
        }
        return string.toString();
    }
}
