public class DeleteWrongFormatException extends WrongFormatException {

    public DeleteWrongFormatException() {
        super("delete");
    }

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + "single whitespace and an integer";
    }
}