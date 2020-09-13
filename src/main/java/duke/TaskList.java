package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * TaskList for manipulating user tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Loads tasks from saveFilePath.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void addTask(Task task) {
        assert task != null : "Task being added is null";
        tasks.add(task);
    }

    /**
     * Parse add task command and adds corresponding task.
     * @param command User command String
     * @param userCommandType UserCommandType
     * @return String array of output messages
     * @throws Parser.InvalidCommandException if user command syntax is invalid
     */
    public String[] addTask(String command, UserCommandType userCommandType)
            throws Parser.InvalidCommandException {
        assert command != null : "Command is null";
        assert userCommandType != null : "userCommandType is null";
        Task task;
        String[] taskComponents = Parser.parseTask(command);

        switch (userCommandType) {
        case TODO:
            task = new Todo(taskComponents[0], false);
            break;
        case DEADLINE:
            task = new Deadline(taskComponents[0], false,
                    Parser.parseDateTime(taskComponents[1]));
            break;
        case EVENT:
            task = new Event(taskComponents[0], false, taskComponents[1]);
            break;
        default:
            throw new Parser.InvalidCommandException();
        }

        for (Task existingTask: tasks) {
            if (existingTask.equals(task)) {
                return new String[]{ String.format("Task \"%s\" already exists", task.getName()) };
            }
        }

        addTask(task);
        return new String[] {
            StringConstants.ADD_MESSAGE,
            "  " + task.toString(),
            String.format(StringConstants.COUNT_MESSAGE, tasks.size())
        };
    }

    /**
     * Mark task done at index.
     * @param index index of task to be marked done
     * @return String array of messages to be printed
     * @throws InvalidIndexException if index is out of bounds
     */
    public String[] markTaskDoneAtIndex(int index) throws InvalidIndexException {
        try {
            Task task = tasks.get(index);
            task.markDone();
            return new String[] {
                StringConstants.DONE_MESSAGE,
                "  " + task.toString()
            };
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Delete task at index.
     * @param index index of task to delete
     * @return String array of messages to be printed
     * @throws InvalidIndexException if index is out of bounds
     */
    public String[] deleteTaskAtIndex(int index) throws InvalidIndexException {
        try {
            Task task = tasks.get(index);
            tasks.remove(index);
            return new String[] {
                StringConstants.DELETE_MESSAGE,
                "  " + task.toString(),
                String.format(StringConstants.COUNT_MESSAGE, tasks.size())
            };
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Gets task at index.
     * @param index index of task to get
     * @return task at index
     * @throws InvalidIndexException if index is out of bounds
     */
    public Task getTaskAtIndex(int index) throws InvalidIndexException {
        try {
            return tasks.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Gets tasks' String representations as String array
     * @return String array of tasks' print Strings
     */
    public String[] getTasksAsStrings() {
        String[] messages = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            messages[i] = String.format("%d.%s", i + 1, task.toString());
        }
        return messages;
    }

    /**
     * Finds tasks matching with keyword String and prints them.
     * @param command user command String
     * @return String array of output messages
     */
    public String[] find(String command) throws Parser.InvalidCommandException {
        assert command != null : "Command is null";
        String keyWordString = Parser.getFindKeyWordString(command);
        ArrayList<String> messages = new ArrayList<>();
        messages.add(StringConstants.TASK_FOUND_MESSAGE);
        int i = 1;
        for (Task task: tasks) {
            if (task.getName().contains(keyWordString)) {
                messages.add(String.format("%d.%s", i, task.toString()));
                i++;
            }
        }
        if (messages.size() == 1) {
            return new String[]{StringConstants.NO_TASK_FOUND_MESSAGE};
        } else {
            return messages.toArray(new String[0]);
        }
    }

    /**
     * Saves user tasks to disk.
     */
    public void saveTaskList() throws IOException {
        Storage.save(tasks);
    }

    /**
     * Exception for when task index is out of bounds.
     */
    public static class InvalidIndexException extends Exception {
        public InvalidIndexException() {
            super("Invalid task index");
        }
    }
}
