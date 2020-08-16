public class DescriptionException extends DukeException {
    protected String command;
    DescriptionException(String command) {
        this.command = command;
    }

    public String toString() {
        return "Oops! The description for " + this.command + " cannot be empty!";
    }
}
