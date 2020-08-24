public class ListCommand extends Command {
    protected String input;
    protected String[] inputWords;

    public ListCommand() {
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.printList();
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
