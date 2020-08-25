abstract public class Command {

    protected final String command;

    protected Command(String command) {
        this.command = command;
    }

    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException;

    public boolean isExit() {
        return false;
    };
}
