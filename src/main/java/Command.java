//public enum Command {
//    list, todo, event, deadline, done, delete
//}

public abstract class Command {

    private String command;
    protected boolean isExit;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

    @Override
    public String toString() {
        return command;
    }
}