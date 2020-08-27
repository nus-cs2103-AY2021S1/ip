public class PandaBotLoadingTasksErrorException extends PandaBotException {
    
    public PandaBotLoadingTasksErrorException(String msg) {
        super("Error in loading task: " + msg);
    }
}
