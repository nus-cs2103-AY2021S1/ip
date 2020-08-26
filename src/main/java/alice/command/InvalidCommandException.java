package alice.command;

import alice.AliceException;

public class InvalidCommandException extends AliceException {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
