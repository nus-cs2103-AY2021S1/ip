public class ByeCommand extends Command {
    public static final boolean IS_EXIT = true;

    public ByeCommand() {
        super(IS_EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
