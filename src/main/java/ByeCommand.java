public class ByeCommand implements Command {
    static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(BYE_MESSAGE);
    }
}
