public class Duke {
    private final Divider divider;
    private final Command command;

    public Duke() {
        divider = new Divider();
        command = new Command();
    }

    public void sendGreeting() {
        System.out.println(divider.wrapInDivider("Hello! I'm Duke\n What can I do for you?"));
    }

    public void receiveCommands() {
        command.getCommands();
    }

}
