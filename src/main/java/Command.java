public interface Command {

    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    boolean isExit();

}
