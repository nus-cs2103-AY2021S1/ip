public class Command {
    boolean isExit = false;

    public Command() {
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.update(tasks);
    }
}
