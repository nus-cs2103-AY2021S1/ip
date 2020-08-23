package MugException;

public class MugException extends Exception {

    public MugException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Wrong command given/lack of information";
    }
}
