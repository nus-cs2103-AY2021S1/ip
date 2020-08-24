/**
 * Represents the user interface that deals with user interactions.
 */
public class Ui {

    protected void start() {
        System.out.println("Hi there! I'm Peanut.\nHow can I be of assistance?\n");
    }

    protected void end() {
        System.out.println("\nBye! Sad to see you go :(");
    }

    protected void showError(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
