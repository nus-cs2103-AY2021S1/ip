package command;
public class TodoCommand extends Command {
    public TodoCommand (String ...parameter) {
        super(parameter);
    }

    @Override
    public Result execute() {
        return null;
    }
}
