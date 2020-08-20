public class MissingDescriptionException extends MissingElementException {
    public MissingDescriptionException(InputType type) {
        super("The description of " + type.toString().toLowerCase() + " cannot be empty.");
    }
}
