import java.time.format.DateTimeParseException;

public class TodoCommand extends Command {
    TodoCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }

    @Override
    public String execute() {
        String result;
        try {
            if (arguments == null || arguments.equals("")) {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.TODO);
            }
            String todoName = arguments;
            Todo newTodo = new Todo(todoName);
            taskList.addTask(newTodo);
            result = ui.showAddTaskMessage(newTodo, taskList);
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        } catch (DateTimeParseException e) {
            result = ui.showErrorMessage("Please key in date and time as follows: DD-MM-YYYY HHMM");
        }
        return result;
    }
}
