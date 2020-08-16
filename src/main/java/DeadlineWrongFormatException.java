public class DeadlineWrongFormatException extends WrongFormatException {

    public DeadlineWrongFormatException() {
        super("deadline");
    }

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + "description of a task in the " +
                "following format:\ntask /by deadline.";
    }
}
