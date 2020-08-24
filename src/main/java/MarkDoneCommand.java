public class MarkDoneCommand extends Command {
    protected String input;

    public MarkDoneCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.markDone(storage, this.input);
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
