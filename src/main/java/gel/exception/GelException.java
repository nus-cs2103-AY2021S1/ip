package gel.exception;

public class GelException extends Exception {

    public GelException() {
        super("\n    LOL! Please use the bye tag if you wanna waste my time ");
    }

    public GelException(String msg) {
        super("\n" + msg);
    }
}
