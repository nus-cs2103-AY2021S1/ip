public abstract class Command {

    public String inputCommand;


    public Command(String inputCommand) { this.inputCommand = inputCommand; }


    public abstract void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException;

    public abstract boolean isExit();
}
