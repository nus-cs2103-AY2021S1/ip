public class DeleteCommand extends Command {
    DeleteCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }

    @Override
    public String execute() {
        String result;
        try {
            if(arguments == null || arguments == "") {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DELETE);
            }
            int index = Integer.parseInt(arguments);
            result = ui.showDeleteTaskMessage(index, taskList);
            taskList.deleteTask(index);
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        }
        return result;
    }
}
