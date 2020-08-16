public class DoneWrongFormatException extends WrongFormatException {

    public DoneWrongFormatException() {
        super("done");
    }

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + "single whitespace and an integer";
    }
}
