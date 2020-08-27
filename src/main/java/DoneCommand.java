public class DoneCommand extends Command {

    private String input;

    public DoneCommand(String input) {
        this.exit = false;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Parser.isValidIndex(input, tasks.getListSize())) {
            Task task = tasks.getList().get(Parser.getIndex(input));
            task.markAsDone();
            ui.doneMessage(task);
            storage.saveListToFile(tasks.getList());
        } else {
            throw new DukeException("You don't have such task in your list...");
        }
    }

}
