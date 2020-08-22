public interface Command {
    public void execute(Ui ui, TaskList tasks) throws InvalidTaskException;

    public boolean hasCommand();
}
