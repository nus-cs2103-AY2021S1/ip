public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static void tryAgain() {
        System.out.println();
        System.out.println("Please try again: ");
    }
}
