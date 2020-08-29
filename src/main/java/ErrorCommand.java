public class ErrorCommand extends Command {
    String errMsg;
    
    public ErrorCommand(String errMsg) {
        this.errMsg = errMsg;
    }
    @Override
    public String execute() throws DukeException {
        throw new DukeException(errMsg);
    }
}
