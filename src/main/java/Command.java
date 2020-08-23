public abstract class Command {
    protected String typeOfCommand;

    public String getCommandType() {
        return typeOfCommand;
    }

    public abstract void runCommand(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean exitCheck();
}