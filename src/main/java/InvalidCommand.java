public class InvalidCommand implements Command {

    private final String toSend;

    public InvalidCommand(String toSend) {
        this.toSend = toSend;
    }

    public InvalidCommand() {
        toSend = "Sorry I do not know what that means :(";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage(toSend);
    }
}
