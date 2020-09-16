public class DoneCommand extends Command {
    DoneCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }

    @Override
    public String execute() {
        String result;
        try {
            if(arguments == null || arguments == "") {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.DONE);
            }
            int index = Integer.parseInt(arguments);
            taskList.markDone(index);
            result = ui.showTasksAfterMarkDone(index, taskList);
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        }
        return result;
    }
}