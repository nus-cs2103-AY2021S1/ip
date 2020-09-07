/**
 * An exit command
 */
class ExitCommand extends Command {
    ExitCommand() {
        super();
        this.exit = true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Duke is exiting, goodbye!";
    }
}
