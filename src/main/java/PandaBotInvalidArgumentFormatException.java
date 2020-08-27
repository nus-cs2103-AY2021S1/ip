public class PandaBotInvalidArgumentFormatException extends PandaBotException {
    
    public PandaBotInvalidArgumentFormatException(String msg) {
        super("Invalid format given: " + msg);
    }
}
