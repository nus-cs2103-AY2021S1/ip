package exception;

public class UnknownCommandException extends Exception {

    @Override
    public String getMessage() {
        return "     I'm sorry, but my maker says that command is unknown :'( ";
    }
}
