import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

/**
 * Class representing commands to add task to TaskList
 */
public class AddCommand extends Command {

    // Attributes
    private final TaskType type;
    private final String description;

    // Constructor

    /**
     * Creates a new command to add task to the list
     * @param type Type of task to be added to the list.
     * @param description Description of task.
     */
    public AddCommand(TaskType type, String description) {
        this.type = type;
        this.description = description;
    }

    // Methods

    /**
     * Executes the command to add a task by adding task into given TaskList.
     *
     * @param tasks   TaskList representing list of current tasks.
     * @param ui      Ui object to handle printing of outputs.
     * @param storage Storage object to handle saving of outputs to computer
     * @throws EmptyBodyException    If command is to add event or deadline but no date and time is provided.
     * @throws UnknownInputException If an unrecognised command is given.
     */
    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage, Map<String, Runnable> runnables)
            throws EmptyBodyException, UnknownInputException {
        assert tasks != null : "tasks shouldn't be null";
        assert ui != null : "ui shouldn't be null";
        assert storage != null : "storage shouldn't be null";
        assert runnables != null : "runnables shouldn't be null";

        switch (this.type) {
        case TODO: {
            Task newTodo = new Todo(description);
            return tasks.createTask(newTodo);
        }
        case DEADLINE: {
            String[] text = description.split(" /by ");
            if (text.length <= 1) {
                throw new EmptyBodyException("deadline", "deadline");
            }
            String description = text[0];
            String stringDeadline = text[1];
            try {
                LocalDate deadline = LocalDate.parse(stringDeadline);
                Task newDeadline = new Deadline(description, deadline);
                return tasks.createTask(newDeadline);
            } catch (DateTimeParseException e) {
                throw new UnknownInputException(stringDeadline);
            }
        }
        case EVENT: {
            String[] text = description.split(" /at ");
            if (text.length <= 1) {
                throw new EmptyBodyException("date and time", "event");
            }
            String description = text[0];
            String dateTime = text[1];
            Task newEvent = new Event(description, dateTime);
            return tasks.createTask(newEvent);
        }
        default:
            throw new UnknownInputException(this.type.toString());
        }
    }

    /**
     * Returns whether the command is a command to exit.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
