public class ByeCommand implements Command {
    static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public void execute() {
        new Duke().print(BYE_MESSAGE);
    }
}
