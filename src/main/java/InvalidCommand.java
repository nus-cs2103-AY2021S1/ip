public class InvalidCommand extends Command {

    public InvalidCommand(String input) {
        super(input);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new InvalidInputException("I'm sorry, but I don't know what that means! :-(");
    }

    @Override
    boolean isExit() {
        return false;
    }
}
