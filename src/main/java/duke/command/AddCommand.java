package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents an add command. An AddCommand object represents a command
 * to insert a task into a TaskList. This task can be either a todo, deadline or event.
 */
public class AddCommand extends Command {

    /** Done status of the task associated with the command. */
    private boolean isDone;

    /**
     * Creates a new AddCommand and initialises its done status to false.
     *
     * @param userStrings Tokenized array form of the input command string.
     */
    public AddCommand(String[] userStrings) {
        super(userStrings);
        isDone = false;
    }

    /**
     * Creates a new AddCommand and allows the initialisation of its done status.
     *
     * @param userStrings Tokenized array form of the input command string.
     * @param isDone The done status to set for the task represented by the command.
     */
    public AddCommand(String[] userStrings, boolean isDone) {
        super(userStrings);
        this.isDone = isDone;
    }

    /**
     * Processes the string array and returns the correct task to be added into the task list.
     * This can be a todo task with the task name, a deadline task with a date and/or time,
     * or an event task with a date and/or time.
     *
     * @param delimiter Represents the delimiter that is used to separate names and the date of the task.
     * @param taskType Represents the type of the task (todo, deadline, event).
     * @return The task to be added into the task list.
     * @throws DukeException If task string does not contain dates (for deadline and event tasks),
     * or has wrong date/time formatting.
     */
    protected Task processTask(String delimiter, String taskType) throws DukeException {
        String taskName = new TaskNameStringChecker(getArray()).checkTaskString(delimiter);
        if (taskType.equals("todo")) {
            return new Todo(taskName);
        } else {
            DateTime dateTime = new DateTimeStringChecker(getArray()).checkDateTime(delimiter);
            return (taskType.equals("deadline")
                    ? new Deadline(taskName, dateTime)
                    : new Event(taskName, dateTime));
        }
    }


    /**
     * Carries out the addition of a task to the task list specified.
     *
     * @param tasks The task list to add the new task into.
     * @return The same task that is added to the task list.
     * @throws DukeException If task string does not contain task name, is unrecognized,
     * or the delimiter used to process deadline/event tasks.
     */
    private Task addTask(TaskList tasks) throws DukeException {
        switch (getArray()[0]) {
        case ("todo"):
            Task todo = processTask("", "todo");
            tasks.addTask(todo);
            return todo;
        case ("deadline"):
            Task deadline = processTask("/by", "deadline");
            tasks.addTask(deadline);
            return deadline;
        case ("event"):
            Task event = processTask("/at", "event");
            tasks.addTask(event);
            return event;
        default:
            throw new DukeException("I don't understand what task you want to be added! Only deadline/todo/event!");
        }

    }

    /**
     * Carries out the addition of a task from a local file to the task list specified.
     *
     * @param tasks The task list to operate on.
     * @throws DukeException If the addition of the task fails.
     */
    public void executeFromFile(TaskList tasks) throws DukeException {
        Task task = addTask(tasks);
        if (isDone) {
            task.markDone();
        }
    }

    /**
     * Executes the addition of tasks and prints notifications to users once that is successful.
     * Also writes the task list to a user-specified file.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     * @throws DukeException If the addition of the task fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        addTask(tasks);
        storage.write(tasks);
        return ui.showTaskAdded(tasks);
    }

}
