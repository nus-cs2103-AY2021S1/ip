package exception;

public class CommandException extends DukeException {
    private String command;
    public CommandException(String command) {
        this.command = command;
    }

    public String toString() {
        return "Sorry, I do not understand what " + this.command + " means :(";
    }
}
