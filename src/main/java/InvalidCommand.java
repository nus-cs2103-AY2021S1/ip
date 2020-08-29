public class InvalidCommand extends Command {
    InvalidCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("oops! im sorry, but i do not know what that means :-(");
    }
}
