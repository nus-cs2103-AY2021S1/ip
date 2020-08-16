public class CommandException extends DukeException {
    protected String command;
    CommandException(String command) {
        this.command = command;
    }

    public String toString() {
        return "Sorry, I do not understand what " + this.command + " means :(";
    }
}
