public interface Command {

    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    boolean isExit();

}
