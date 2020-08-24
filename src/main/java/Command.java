public abstract class Command {

    protected final String args;

    protected Command(String args) {
        this.args = args;
    }

    protected boolean isExit() {
        return false;
    }

    public abstract String execute(TaskList taskList, Storage storage)
            throws TaskException, StorageException;
}
