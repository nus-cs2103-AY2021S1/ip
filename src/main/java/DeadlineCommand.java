import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    DeadlineCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }

    @Override
    public String execute() {
        String result;
        try {
            if(arguments == null || arguments.equals("")) {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DEADLINE);
            }
            String[] deadlineArguments = Parser.splitDeadlineArguments(arguments);
            if(deadlineArguments.length < 2) {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DEADLINE);
            }
            String deadlineName = deadlineArguments[0];
            String deadlineTime = deadlineArguments[1];
            Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
            taskList.addTask(newDeadline);
            result = ui.showAddTaskMessage(newDeadline, taskList);
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        } catch (DateTimeParseException e) {
            result = ui.showErrorMessage("Please key in date and time as follows: DD-MM-YYYY HHMM");
        }
        return result;
    }
}