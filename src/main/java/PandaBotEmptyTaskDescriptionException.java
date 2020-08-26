public class PandaBotEmptyTaskDescriptionException extends PandaBotException{

    public PandaBotEmptyTaskDescriptionException(String taskName) {
        super(String.format(":c OOPS! The description of the %s cannot be empty.", taskName));
    }
}
