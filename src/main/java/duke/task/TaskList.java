package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.util.Parser;
import duke.util.Strings;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor
     *
     * @param tasks List of task objects.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the list, according to user's input.
     * @param input User's input
     */
    public void addTask(String input) throws DukeException {
        String[] split = input.split(" ", 2);

        if (split.length < 2) {
            throw new DukeException(Strings.ERROR_TODO_DESCRIPTION_EMPTY);
        } else {
            String keyword = split[0];
            String description = split[1];

            switch (keyword) {
            case "todo":
                Task task = new ToDo(description, false);
                this.tasks.add(task);
                break;
            case "deadline":
                String[] splitSlash = description.split(" /by ");
                if (splitSlash.length != 2) {
                    throw new DukeException(Strings.ERROR_DEADLINE_FORMAT_INCORRECT);
                }
                task = new Deadline(splitSlash[0], false, Parser.parseDate(splitSlash[1]));
                this.tasks.add(task);
                break;
            case "event":
                splitSlash = description.split(" /at ");
                if (splitSlash.length != 2) {
                    throw new DukeException(Strings.ERROR_EVENT_FORMAT_INCORRECT);
                }
                task = new Event(splitSlash[0], false, Parser.parseDate(splitSlash[1]));
                this.tasks.add(task);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Sets the task's isDone status to true.
     * @param index Index of the task in the task list.
     */
    public void markTaskAsDone(int index) throws DukeException {
        Task task = this.tasks.get(index);
        task.toggleIsDone();
        try {
            this.tasks.set(index, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Strings.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException e) {
            throw new DukeException(Strings.ERROR_DONE_FORMAT_INCORRECT);
        }
    }

    /**
     * Removes the (index + 1)th task from the task list.
     *
     * @param index Index of the to-be-deleted task in the task list.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Strings.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException e) {
            throw new DukeException(Strings.ERROR_DELETE_FORMAT_INCORRECT);
        }
    }

    /**
     * Returns the list of tasks.
     */
    public TaskList findTask(String taskKeyword) {
        List<Task> matchedTasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.getDescription().contains(taskKeyword)) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }

    /**
     * Returns the list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns task at the specific index.
     *
     * @param index Task index
     * @return Task at the specific index.
     */
    public Task getTaskAtIndex(int index) {
        return tasks.get(index);
    }

    public int getLength() {
        return tasks.size();
    }
}
