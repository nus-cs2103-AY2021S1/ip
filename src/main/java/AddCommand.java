import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class representing commands to add task to TaskList
 */
public class AddCommand extends Command {

    // Attributes
    private final String type;
    private final String remaining;

    // Constructor
    public AddCommand(String type, String remaining) {
        this.type = type;
        this.remaining = remaining;
    }

    // Methods

    /**
     * Executes the command to add a task by adding task into given TaskList.
     * @param tasks TaskList representing list of current tasks.
     * @param ui Ui object to handle printing of outputs.
     * @param storage Storage object to handle saving of outputs to computer
     * @throws EmptyBodyException If command is to add event or deadline but no date and time is provided.
     * @throws UnknownInputException If an unrecognised command is given.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyBodyException, UnknownInputException {
        switch (this.type) {
            case "todo":
                Task newTodo = new Todo(remaining);
                ui.showMessage(tasks.createTask(newTodo));
                break;
            case "deadline": {
                String[] text = remaining.split(" /by ");
                String description = text[0];
                if (text.length <= 1) {
                    throw new EmptyBodyException("deadline", "deadline");
                }
                try {
                    LocalDate deadline = LocalDate.parse(text[1]);
                    Task newDeadline = new Deadline(description, deadline);
                    ui.showMessage(tasks.createTask(newDeadline));
                } catch (DateTimeParseException e) {
                    throw new UnknownInputException(text[1]);
                }
                break;
            }
            case "event": {
                String[] text = remaining.split(" /at ");
                String description = text[0];
                if (text.length <= 1) {
                    throw new EmptyBodyException("date and time", "event");
                }
                String dateTime = text[1];
                Task newEvent = new Event(description, dateTime);
                ui.showMessage(tasks.createTask(newEvent));
                break;
            }
            default:
                throw new UnknownInputException(this.type);
        }
    }

    /**
     * Returns whether the command is a command to exit.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
