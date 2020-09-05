package nekochan.exceptions;

import nekochan.util.Messages;

public class NekoException extends RuntimeException {

    public NekoException(String message) {
        super(String.format(Messages.ERROR_MESSAGE_HEADER, message));
    }
}
