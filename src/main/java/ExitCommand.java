public class ExitCommand extends Command {
    ExitCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(ui.exit());
    }
}
