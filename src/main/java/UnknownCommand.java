public class UnknownCommand extends Command {

    public UnknownCommand() {
        super("unknown");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeUnknownInputException {
        throw new DukeUnknownInputException();
    }

}
