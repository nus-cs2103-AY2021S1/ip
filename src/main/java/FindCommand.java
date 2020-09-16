public class FindCommand extends Command {
    FindCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }

    @Override
    public String execute() {
        String result;
        try {
            if(arguments == null || arguments.equals("")) {
                throw new DukeIllegalArgumentException("", DukeException.DukeExceptionType.FIND);
            }
            String word = arguments;
            result = ui.findTask(word, taskList);
        } catch (DukeException e) {
            result = ui.showErrorMessage(e);
        }
        return result;
        
    }
}