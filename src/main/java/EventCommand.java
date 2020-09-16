import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    EventCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }

    @Override
    public String execute() {
        String result;
        try {
            if(arguments == null || arguments.equals("")) {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.EVENT);
            }
            String[] eventDescriptionAndTime = Parser.splitEventArguments(arguments);
            if(eventDescriptionAndTime.length < 2) {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.EVENT);
            }
            String eventName = eventDescriptionAndTime[0];
            String eventTime = eventDescriptionAndTime[1];
            Event newEvent = new Event(eventName, eventTime);
            taskList.addTask(newEvent);
            result = ui.showAddTaskMessage(newEvent, taskList);
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        } catch (DateTimeParseException e) {
            result = ui.showErrorMessage("Please key in date and time as follows: DD-MM-YYYY HHMM");
        }
        return result;
    }
}