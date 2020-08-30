import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main method to launch the GUI.
     *
     * @param args arguments for the main method.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
