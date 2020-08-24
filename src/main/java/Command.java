public abstract class Command {

    protected boolean isExit;

    /**
     * Creates an abstract Command Object.
     *
     * @param isExit whether the programme should end
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
