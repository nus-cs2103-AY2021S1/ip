public class PandaBotOutOfRangeException extends PandaBotException{
    
    public PandaBotOutOfRangeException() {
        super("Task number given is not in range. Which task are you referring to?");
    }
}
