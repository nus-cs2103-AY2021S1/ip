package duke;

public class InvalidCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Invalid command");
    }
}
