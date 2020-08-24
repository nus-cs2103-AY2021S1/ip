public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;
    public boolean isExit();
}
