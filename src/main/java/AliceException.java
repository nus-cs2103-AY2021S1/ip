public class AliceException extends Exception {
    AliceException(String msg) {
        super("Oh no! " + msg);
    }
}
