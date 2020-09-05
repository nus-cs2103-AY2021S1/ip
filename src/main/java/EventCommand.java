import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String command;

    EventCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyEventTimeException, DukeEmptyEventException,
            DukeTimeParseException {
        String message = "";
        try {

            if (this.command.split(" ").length == 1) {
                throw new DukeEmptyEventException(this.command);
            }
            String eventer = Parser.stringBuilder(command.split(" "), 1, command.split(" ").length - 1);
            String[] eventParts = eventer.split(" /at ");
            if (eventParts.length == 1) {
                throw new DukeEmptyEventTimeException(command);
            }
            Event eventTask = new Event(eventParts[0], eventParts[1]);
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
