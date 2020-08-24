public class DoneCommand implements Command {

    protected static final String DONE_MESSAGE = "Nice! I've marked this task as done: ";

    private int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (doneIndex > taskList.size()) {
            throw new DukeException("No such task!");
        }
        Task doneTask = taskList.get(doneIndex);
        doneTask.markDone();
        ui.outputMessage(createDoneMessage(doneTask));
        storage.updateFile(taskList);
    }

    private String createDoneMessage(Task taskDone) {
        return DONE_MESSAGE + "\n   " + taskDone;
    }






}
