public class UnknownCommandException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessage() + " I'm sorry, but I " +
                "don't know what that means :(";
    }
}
