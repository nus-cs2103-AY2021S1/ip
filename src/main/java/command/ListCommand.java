package command;

public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String sendRequest() {
        return "retrieval";
    }
}
