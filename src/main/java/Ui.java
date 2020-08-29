import java.util.Scanner;

/**
 * A class of User Interface.
 */
public class Ui {
    private static final String TAB = "  ";
    private static final String LINE_BREAKER = TAB
            + "_________________________________________________________________";
    private static final String GREET = TAB + " Hello! I'm Duke" + "\n" + TAB + " What can I do for you?";

    protected final Scanner sc;

    /**
     * Constructor.
     * Instantiate a scanner to read the user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the opening greeting.
     */
    public void showWelcome() {
        System.out.println(LINE_BREAKER);
        System.out.println(GREET);
        System.out.println(LINE_BREAKER);
    }

    /**
     * Returns the divider.
     */
    public void showLine() {
        System.out.println(LINE_BREAKER);
    }

    /**
     * Returns the error message of the loading issue.
     */
    public void showLoadingError() {
        System.out.println(TAB + "Loading fails");
    }

    /**
     * Returns the error message.
     * @param msg error message from the exception.
     */
    public void showError(String msg) {
        System.out.println(TAB + msg);
    }

    /**
     * Reads the user input.
     * @return the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
