public class FindCommand extends Command {
    String input;
            
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTask(input);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
