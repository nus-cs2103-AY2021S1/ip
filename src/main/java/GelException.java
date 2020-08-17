public class GelException extends Exception {

    public GelException() {
        super("\n    LOL! Please read the user guide before talking to me again...");
    }

    public GelException(String msg) {
        super("\n" + msg);
    }
}
