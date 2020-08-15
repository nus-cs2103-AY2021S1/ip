package command;

/**
 * A subclass of Command which sends request terminate the bot.
 */
public class ExitCommand extends Command {
    public ExitCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String sendRequest() {
        return "end";
    }
}
