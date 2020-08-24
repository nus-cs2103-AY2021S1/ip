abstract class Command {

    // Abstract Methods
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    abstract boolean isExit();

}
