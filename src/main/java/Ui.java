/**
 * Represents the user interface that deals with user interactions.
 */
public class Ui {

    private String message;

    private Ui(String message) {
        this.message = message;
    }

    protected static Ui makeStartUi() {
        return new Ui("Hi there! I'm Peanut.\nHow can I be of assistance?\n");
    }

    protected static Ui makeEndUi() {
        return new Ui("\nBye! Sad to see you go :(");
    }

    protected static Ui makeErrorUi(Exception exception) {
        return new Ui(exception.getMessage());
    }

    protected static Ui makeUi(String message) {
        return new Ui(message);
    }

    protected void printMessage() {
        System.out.println(message);
    }

    protected String getMessage() {
        return message;
    }

}
