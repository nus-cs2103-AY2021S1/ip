public class UnknownCommand extends Command {

    String gibberish;

    public UnknownCommand(String gibberish) {
        this.gibberish = gibberish;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(String.format("I'm sorry, but I don't know what \"%s\" means :-(", gibberish));
    }
}
