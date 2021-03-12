public class ByeCommand extends Command {
    public String commandWord = "bye";

    public ByeCommand() {
        super("bye");
    }

    @Override
    public String execute() {
        return Ui.bye();
    }
}
