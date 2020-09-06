import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method for Duke.
     * @param args arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
