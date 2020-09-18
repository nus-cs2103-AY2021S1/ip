public class Duke {
    private Command command;

    public Duke() {
        command = new Command(new Storage("data/Duke.txt"));
    }

    String getResponse(String input) {
        return command.parseToCommand(input);
    }
}
