package raythx98.duke.ui;

/**
 * Deals with the user interface.
 */
public class Ui {

    private String toShowOnScreen = "";

    /**
     * Prints a greeting message.
     */
    public String greet() {
        return "Hello mah dud, itza handsome robo speakin\n"
                + "What duh hell du yu wan?";
    }

    /**
     * Prints a farewell message.
     */
    public void farewell() {
        toShowOnScreen += "Never come back,\n"
                + "dun wanna see yu ever agin";
    }

    public void resetString() {
        toShowOnScreen = "";
    }

    public void addShowOnScreen(String string) {
        toShowOnScreen += string;
    }

    public String finalShowOnScreen() {
        return toShowOnScreen;
    }

    /**
     * Prints the error message.
     *
     * @param e String of error to be printed.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }
}
