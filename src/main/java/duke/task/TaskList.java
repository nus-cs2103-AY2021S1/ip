package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.ui.Ui;
import duke.util.MagicStrings;
import duke.util.Parser;

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
     *
     * @param input User's input
     */
    public String addTask(String input, Ui ui) throws DukeException {
        String[] split = input.split(" ", 2);

        if (split.length < 2) {
            throw new DukeException(MagicStrings.ERROR_TODO_DESCRIPTION_EMPTY);
        }

        String keyword = split[0];
        String description = split[1];
        String log = "";

        switch (keyword) {
        case "todo":
            Task task = new ToDo(description, false);
            this.tasks.add(task);
            log = ui.printTaskAdded(task);
            break;
        case "deadline":
            String[] splitSlash = description.split(" /by ");
            if (splitSlash.length != 2) {
                throw new DukeException(MagicStrings.ERROR_DEADLINE_FORMAT_INCORRECT);
            }
            task = new Deadline(splitSlash[0], false, Parser.parseDate(splitSlash[1]));
            this.tasks.add(task);
            log = ui.printTaskAdded(task);
            break;
        case "event":
            splitSlash = description.split(" /at ");
            if (splitSlash.length != 2) {
                throw new DukeException(MagicStrings.ERROR_EVENT_FORMAT_INCORRECT);
            }
            task = new Event(splitSlash[0], false, Parser.parseDate(splitSlash[1]));
            this.tasks.add(task);
            log = ui.printTaskAdded(task);
            break;
        default:
            break;
        }
        return log;
    }

    /**
     * Sets the task's isDone status to true.
     *
     * @param command User's command.
     */
    public String markTaskAsDone(String command, Ui ui) throws DukeException {
        String[] split = Parser.splitCommand(command);
        int index = Integer.parseInt(split[1]) - 1;

        Task task = this.tasks.get(index);
        Task updatedTask = new Task(task.description, true);

        try {
            this.tasks.set(index, updatedTask);
            return ui.printTaskAsDone(updatedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MagicStrings.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException e) {
            throw new DukeException(MagicStrings.ERROR_DONE_FORMAT_INCORRECT);
        }
    }

    /**
     * Removes the (index + 1)th task from the task list.
     *
     * @param command Index of the to-be-deleted task in the task list.
     */
    public String deleteTask(String command, Ui ui) throws DukeException {
        String[] split = Parser.splitCommand(command);
        int index = Integer.parseInt(split[1]) - 1;

        try {
            Task task = this.tasks.get(index);
            this.tasks.remove(task);
            return ui.printTaskDeleted(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MagicStrings.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException e) {
            throw new DukeException(MagicStrings.ERROR_DELETE_FORMAT_INCORRECT);
        }
    }

    /**
     * Edit the (index + 1)th task from the task list.
     *
     * @param command User's command description
     */
    public String editTask(String command, Ui ui) throws DukeException {
        try {
            String[] keyword = Parser.splitCommand(command);
            String[] rest = Parser.splitCommand(keyword[1]);

            int index = Integer.parseInt(rest[0]) - 1;
            String newDesc = rest[1];

            Task task = tasks.get(index);
            Task newTask;

            if (task instanceof ToDo) {
                newTask = new ToDo(newDesc, task.getIsDone());
            } else if (task instanceof Deadline) {
                newTask = new Deadline(newDesc, task.getIsDone(), ((Deadline) task).getDoBy());
            } else {
                assert task instanceof Event : "Task can't be of Task (parent) type";

                newTask = new Event(newDesc, task.getIsDone(), ((Event) task).getTime());
            }
            this.tasks.set(index, newTask);
            return ui.printTaskEdited(newTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(MagicStrings.ERROR_EDIT_FORMAT_INCORRECT);
        }
    }

    /**
     * Returns the list of tasks.
     */
    public TaskList findTask(String command) throws DukeException {
        try {
            String[] split = Parser.splitCommand(command);

            List<Task> matchedTasks = new ArrayList<>();

            for (Task task : this.tasks) {
                if (task.getDescription().contains(split[1])) {
                    matchedTasks.add(task);
                }
            }
            return new TaskList(matchedTasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException(MagicStrings.ERROR_FIND_FORMAT_INCORRECT);
        }
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
