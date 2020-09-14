import java.time.format.DateTimeParseException;

/**
 * Class that makes a Eventcommand which generates the event task.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Constructs an EventCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    EventCommand(String input) {
        this.input = input;
    }

    /**
     * Generates the Event task.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeEmptyEventException     If an empty event message is keyed in.
     * @throws DukeEmptyEventTimeException If wrong format is keyed in for the event time.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyEventTimeException, DukeEmptyEventException,
            DukeTimeParseException {
        String message = "";
        try {

            if (this.input.split(" ").length == 1) {
                throw new DukeEmptyEventException(this.input);
            }
            String eventMessage = Parser.stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
            String[] eventMessageParts = eventMessage.split(" /at ");
            if (eventMessageParts.length == 1) {
                throw new DukeEmptyEventTimeException(input);
            }
            Event eventTask = new Event(eventMessageParts[0], eventMessageParts[1]);
            tasklist.addTask(eventTask);
            message = ui.printTaskAdd(eventTask, tasklist.numOfTasks());
        } catch (DukeEmptyEventTimeException e) {
            message = e.getMessage();
        } catch (DukeEmptyEventException e) {
            message = e.getMessage();
        } catch (DateTimeParseException e) {
            throw new DukeTimeParseException("");
        }
        return message;
    }
}
