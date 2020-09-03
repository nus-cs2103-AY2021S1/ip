package nekochan.exceptions;

public class NekoException extends RuntimeException {

    public NekoException(String message) {
        super(String.format("Gomen nasai~ %s\n", message));
    }
}
