package command;
public class DeadLineCommand extends Command {
    public DeadLineCommand (String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute() {
        return null;
    }
}
