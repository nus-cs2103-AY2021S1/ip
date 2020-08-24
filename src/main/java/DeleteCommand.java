public class DeleteCommand extends Command {
    protected String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.deleteTask(storage, this.input);
        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
