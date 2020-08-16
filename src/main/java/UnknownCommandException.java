public class UnknownCommandException extends DukeException {

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + " I'm sorry, but I " +
                "don't know what that means :(";
    }
}
