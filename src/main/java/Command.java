import java.io.IOException;

public abstract class Command {
    TaskList taskList;
    Ui ui;
    Storage storage;
    boolean isExit = false;
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        storage.updateFile(taskList);
    }

    public boolean isExit() {
        return isExit;
    }

}
