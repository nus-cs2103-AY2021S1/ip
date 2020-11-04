package dd.commands;

import dd.datetimehandler.DateTimeHandler;
import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.Deadline;
import dd.tasks.Event;
import dd.tasks.TaskList;
import dd.tasks.Todo;
import dd.ui.Ui;

/**
 * An add command of a certain type and user input modifies
 * the task list in the system by adding new tasks.
 */
public class AddCommand extends Command {

    private DateTimeHandler dth;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class Constructor.
     *
     * @param command Command given.
     * @param item Details of item being added.
     */
    public AddCommand(String command, String item) {
        super(command, item);
    }

    private String addTodo() {
        int initialNum = tasks.getTaskSize();
        Todo addedTodo = new Todo(item);
        tasks.addTodo(addedTodo);

        assert (tasks.getTaskSize() - initialNum) == 1 : "failed adding todo task";

        return ui.startAddTodo(addedTodo) + "\n" + ui.printTasksSize(tasks.getTaskSize());
    }

    private String addDeadline() throws DukeException {
        // create array of [task desc, task date]
        String[] temp = item.split(" /by ");

        if (temp.length == 2) {
            boolean isValidInput = dth.isValidInput(temp[1]);

            if (isValidInput) {
                // valid
                int initialNum = tasks.getTaskSize();
                String formattedDate = dth.categorizeInput(temp[1]);
                Deadline addedDeadline = new Deadline(temp[0], formattedDate);
                tasks.addTask(addedDeadline);
                tasks.sortTasks();

                assert (tasks.getTaskSize() - initialNum) == 1 : "failed adding deadline task";

                return ui.startAddDeadline(addedDeadline) + "\n"
                        + ui.printTasksSize(tasks.getTaskSize());
            } else {
                // not valid date
                throw new DukeException().invalidDate();
            }
        } else {
            // no date input
            throw new DukeException().invalidDeadline();
        }
    }

    private String addEvent() throws DukeException {
        // create array of [task desc, task date]
        String[] temp = item.split(" /at ");

        if (temp.length == 2) {
            boolean isValidInput = dth.isValidInput(temp[1]);

            if (isValidInput) {
                // valid
                int initialNum = tasks.getTaskSize();
                String formattedDate = dth.categorizeInput(temp[1]);
                Event addedEvent = new Event(temp[0], formattedDate);
                tasks.addTask(addedEvent);
                tasks.sortTasks();

                assert (tasks.getTaskSize() - initialNum) == 1 : "failed adding event task";

                return ui.startAddEvent(addedEvent) + "\n"
                        + ui.printTasksSize(tasks.getTaskSize());
            } else {
                // not valid date
                throw new DukeException().invalidDate();
            }
        } else {
            // no date input
            throw new DukeException().invalidEvent();
        }
    }

    /**
     * Executes the appropriate method based on command.
     *
     * @param taskList Current TaskList to modify.
     * @param u Ui used to print statements.
     * @param ds DataStorage used to load or write data.
     * @return String to confirm details of task added.
     * @throws DukeException If no date string contained in item,
     * or invalid date string is given to Deadline or Event tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui u, DataStorage ds) throws DukeException {
        tasks = taskList;
        ui = u;
        this.dth = new DateTimeHandler();
        String output = "";

        switch (command) {
        case "todo":
            output = addTodo();
            break;
        case "deadline":
            output = addDeadline();
            break;
        case "event":
            output = addEvent();
            break;
        default:
            assert false : "invalid command: " + command;
        }

        return output;
    }
}
