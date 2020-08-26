public abstract class Command {

    public Command() {}

    public abstract void execute(Tasklist tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
