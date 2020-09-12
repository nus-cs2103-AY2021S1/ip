/**
 * An exit command
 */
class ExitCommand extends Command {
    ExitCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.exit = true;
    }

    @Override
    public String execute() {
        return "Jarvis is exiting, goodbye!";
    }
}
