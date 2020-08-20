package main.java;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String emoji = new String(Character.toChars(0x1F616));
        String msgForException = "    ____________________________________________________________\n"
                + "    " + emoji + this.getMessage() + "\n"
                + "    ____________________________________________________________\n";
        return msgForException;
    }
}
