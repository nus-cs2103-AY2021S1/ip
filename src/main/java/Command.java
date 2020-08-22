public interface Command {
    void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException;

    default boolean isExit() {
        return false;
    }
}
