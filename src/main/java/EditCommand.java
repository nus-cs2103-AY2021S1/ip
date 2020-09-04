public class EditCommand extends Command {
    private CommandType commandType;
    private int index;

    public EditCommand(CommandType commandType, int index) {
        this.commandType = commandType;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        switch (this.commandType) {
        case DONE:
            Task doneTask = taskList.markTaskAsDone(this.index);
            ui.showDoneTask(doneTask.toString());
            storage.replaceLine(this.index, doneTask.toFileString());
            break;
        case DELETE:
            Task deletedTask = taskList.deleteTask(this.index);
            ui.showDeletedTask(deletedTask.toString(), taskList.getNumTasks());
            storage.deleteLine(this.index);
            break;
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
