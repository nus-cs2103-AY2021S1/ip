package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Encapsulates a command that will add a task according the the input it is given.
 */
public class AddCommand extends Command {
    /**
     * The command that will determine the type of task added.
     */
    private final Commands command;

    /**
     * The user given input that determines the details of the task added.
     */
    private final String input;

    /**
     * Initializes a new AddCommand instance.
     *
     * @param command   The command that determines the type of task added.
     * @param input     The details of the task added.
     */
    public AddCommand(Commands command, String input) {
        this.command = command;
        this.input = input;
    }

    /**
     * Executes the Todo, Deadline and Event commands by creating a task according to the command given.
     *
     * @param taskList          The list of tasks known by the chat bot.
     * @param ui                The Ui that is used by the chat bot.
     * @param storage           The storage used by the chat bot.
     * @return                  Chat bot message
     * @throws DukeException    If the execution fails at any step.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        switch (this.command) {
        case TODO:
            Task newTodoTask = new Todo(this.input);
            return addTask(newTodoTask, taskList, ui, storage);

        case DEADLINE:
            try {
                String[] split = this.input.split(" /by ", 2);
                Task newDeadlineTask = new Deadline(split[0], split[1]);
                return addTask(newDeadlineTask, taskList, ui, storage);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a deadline to complete the task by"
                        + " or follow the exact command format!");
            }

        case EVENT:
            try {
                String[] split = this.input.split(" /at ", 2);
                Task newEventTask = new Event(split[0], split[1]);
                return addTask(newEventTask, taskList, ui, storage);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please enter the time at which the event will take place"
                        + " or follow the exact command format!");
            }

        default:
            throw new DukeException("Sorry! I don't recognize the type of task you're tyring to add!");
        }
    }

    /**
     * Adds and stores a new task in the task list and storage then prints out a message indicating that
     * the task was successfully added.
     *
     * @param newTask           The new task to be added
     * @param taskList          The list of tasks known by the chat bot.
     * @param ui                The Ui that is used by the chat bot.
     * @param storage           The storage used by the chat bot.
     * @return                  Chat bot message
     * @throws DukeException    If the execution fails at any step.
     */
    public String addTask(Task newTask, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.addTask(newTask);
            storage.saveTask(newTask);
            String message = String.format("%s\nNow you have %d tasks in the list.",
                    newTask.toString(), taskList.size());
            return ui.replyAdd(message);
        } catch (DukeException e) {
            throw new DukeException("I couldn't add the task to the database!");
        }
    }
}
