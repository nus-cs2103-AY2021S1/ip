public class InvalidCommand extends Command {
    public String errorMsg;

    public InvalidCommand(String errorMsg) {
        super("NA");
        this.errorMsg = errorMsg;
    }

    @Override
    public String execute() {
        return errorMsg;
    }
}
