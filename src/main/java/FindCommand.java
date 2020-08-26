public class FindCommand extends Command {
    String input;
            
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(input);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
