package duke;

public class InvalidCommand implements Command {
    @Override
    public String execute() {
        return "Invalid command";
    }
}
