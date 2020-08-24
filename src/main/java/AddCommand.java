import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    @Override
    public boolean isExit() {
        return false;
    }
}
