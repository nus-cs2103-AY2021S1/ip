public class FindCommand extends Command {
    FindCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks.find(fullCommand.substring(5)));
    }
}
