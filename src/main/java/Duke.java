/**
 * Chatbot class
 */
public class Duke {
    private Command command;

    /**
     * Constructor for Duke
     */
    public Duke() {
        command = new Command(new Storage("data/Duke.txt"));
    }

    String getResponse(String input) {
        return command.parseToCommand(input);
    }
}
