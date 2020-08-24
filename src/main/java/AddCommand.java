public class AddCommand extends Command {
    protected String input;
    protected String[] inputWords;

    public AddCommand(String input, String[] inputWords) {
        this.input = input;
        this.inputWords = inputWords;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            taskList.addTask(storage, this.input, this.inputWords);
        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
