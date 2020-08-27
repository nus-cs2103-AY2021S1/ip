public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException {
        throw AlisonException.defaultException();
    }

}
