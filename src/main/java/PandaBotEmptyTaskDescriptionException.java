public class PandaBotEmptyTaskDescriptionException extends PandaBotException{

    public PandaBotEmptyTaskDescriptionException(String taskName) {
        super(String.format(":c OOPS! The description of a %s cannot be empty.", taskName));
    }
}
