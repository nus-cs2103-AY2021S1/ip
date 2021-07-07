/**
 * chatbot class
 */
public class Duke {
    private Command command;

    /**
     * constructor, initializes command
     */
    public Duke() {
        command = new Command(new Storage("data/Duke.txt"));
    }

    /**
     * passes it to command and returns string
     * @param input user input
     * @return output
     */
    String getResponse(String input) {
        return command.parseToCommand(input);
    }
}
