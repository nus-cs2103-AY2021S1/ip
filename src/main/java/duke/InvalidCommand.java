package duke;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(Command.INVALID);
    }
    public String execute() {
        return Ui.informInvalidCommand();
    }
}
