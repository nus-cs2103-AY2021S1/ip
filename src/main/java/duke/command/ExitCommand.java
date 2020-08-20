package duke.command;

/** A subclass of Command which sends request to terminate the bot */
public class ExitCommand extends Command {
    public ExitCommand() {
        this.content = "bye";
    }

    @Override
    public String sendRequest() {
        return "end";
    }
}
