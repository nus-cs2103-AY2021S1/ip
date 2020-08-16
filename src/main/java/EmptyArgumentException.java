import sun.invoke.empty.Empty;

public class EmptyArgumentException extends Exception {
    EmptyArgumentException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    EmptyArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
