import java.time.format.DateTimeParseException;

public class ScheduleCommand extends Command {
    ScheduleCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }

    @Override
    public String execute() {
        String result;
        try {
            if (arguments == null || arguments.equals("")) {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.VIEW_SCHEDULE);
            }
            String scheduleArguments = arguments;
            String scheduleTime = Parser.splitScheduleArguments(scheduleArguments)[1];
            TaskList matchingTasksOnDate = taskList.getTasksOnDate(scheduleTime);
            result = ui.showCurrentTasks(matchingTasksOnDate);
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        } catch (DateTimeParseException e) {
            result = ui.showErrorMessage("Please key in date and time as follows: DD-MM-YYYY HHMM");
        }
        return result;
    }
}
