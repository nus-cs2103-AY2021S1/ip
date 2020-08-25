public interface Command {
    public boolean isExit();
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
