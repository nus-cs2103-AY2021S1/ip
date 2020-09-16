package duke.command;

import duke.DateTime;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.parser.DateTimeStringChecker;
import duke.parser.TaskNameStringChecker;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;


/**
 * Represents an add command. An AddCommand object represents a command
 * to insert a task into a TaskList. This task can be either a todo, deadline or event.
 */
public class AddCommand extends Command {

    /** Done status of the task associated with the command. */
    private final boolean isDone;

    /**
     * Creates a new AddCommand and initialises its default done status to false.
     *
     * @param userString Tokenized array form of the input command string.
     */
    public AddCommand(String[] userString) {
        super(userString);
        isDone = false;
    }

    /**
     * Creates a new AddCommand and allows specific initialisation of its done status.
     *
     * @param userString Tokenized array form of the input command string.
     * @param isDone The done status to set for the task represented by the command.
     */
    public AddCommand(String[] userString, boolean isDone) {
        super(userString);
        this.isDone = isDone;
    }

    /**
     * Processes the string array and returns the correct task to be added into the task list.
     * This can be a todo task with the task name, a deadline task with a date and/or time,
     * or an event task with a date and/or time.
     *
     * @param taskType Represents the type of the task (todo, deadline, event).
     * @return The task to be added into the task list.
     * @throws DukeException If task string does not contain dates (for deadline and event tasks),
     * or has wrong date/time formatting.
     */
    protected Task processTask(TaskType taskType) throws DukeException {
        String taskName = new TaskNameStringChecker(getStringArray()).checkTaskString(taskType);
        if (taskType.equals(TaskType.TODO)) {
            assert getStringArray().length > 1 : "Length of input string should be longer than 1!";
            return new Todo(taskName);
        }
        assert getStringArray().length >= 4 : "Length of input string should be longer than 4!";
        DateTime dateTime = new DateTimeStringChecker(getStringArray()).checkDateTime(taskType);
        return (taskType.equals(TaskType.DEADLINE)
                ? new Deadline(taskName, dateTime)
                : new Event(taskName, dateTime));
    }

    /**
     * Carries out the addition of a task to the task list specified.
     *
     * @param tasks The task list to add the new task into.
     * @return The same task that is added to the task list.
     * @throws DukeException If task string does not contain task name, is unrecognized.
     */
    protected Task addTask(TaskList tasks) throws DukeException {
        Task task = processTask(Enum.valueOf(TaskType.class, getStringArray()[0].toUpperCase()));
        tasks.addTask(task);
        return task;
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
     * @return String to be printed out.
     * @throws DukeException If the addition of the task fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        addTask(tasks);
        storage.write(tasks);
        return ui.showTaskAdded(tasks);
    }

}
