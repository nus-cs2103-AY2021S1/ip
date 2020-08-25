public abstract class Command {
    public Command(){
    }

    public boolean isExit() {
        return false;
    }

    protected abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
