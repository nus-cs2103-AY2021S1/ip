public class NoCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("I'm sorry, but I don't know what that means.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}