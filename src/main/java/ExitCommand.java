public class ExitCommand extends Command {

    protected ExitCommand(String args) {
        super(args);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskException, StorageException {
        return "See you soon!";
    }
}
