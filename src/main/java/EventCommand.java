import java.time.format.DateTimeParseException;

/**
 * EventCommand class executes event actions.
 * Extends from the Command class.
 */
public class EventCommand extends Command {
    /**
     * Constructor that creates a EventCommand object.
     * @param ui ui that handles user interface
     * @param taskList list of tasks
     * @param args String of event commands
     */
    public EventCommand(Ui ui, TaskList taskList, String args) {
        super(ui, taskList, args);
    }

    /**
     * Executes actions for event command
     * @return String message containing a successful add message
     */
    @Override
    public String action() {
        String result;
        String[] eventArguments = Parser.splitEventArguments(args);
        try {
            if (eventArguments.length < 2) {
                throw new DukeException("The description of a event cannot be empty!");
            }
            String description = eventArguments[0];
            if (!eventArguments[1].equals("")) {
                throw new DukeException("The deadline of the event cannot be empty!");
            } else {
                String due = eventArguments[1];
                Task event = new Events(description, due);
                taskList.addTask(event);
                result = ui.printAdd(taskList, event);
            }
        } catch (DukeException e) {
            result = ui.printDukeError(e);
        } catch (DateTimeParseException e) {
            result = ui.printError("Please use this format: \n"
                    + "dd-MM-yyyy HHmm");
        }
        return result;
    }
}
