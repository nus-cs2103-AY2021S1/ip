public interface Command {

    void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException;

    boolean isExitCmd();
}
