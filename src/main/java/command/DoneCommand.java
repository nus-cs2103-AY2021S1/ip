package command;

/**
 * A subclass of Command which sends request to mark a task in list as done.
 */
public class DoneCommand extends Command {
    public DoneCommand(String userInput) {
        this.content = userInput;
    }

    @Override
    public String sendRequest() {
        return "update";
    }
}
