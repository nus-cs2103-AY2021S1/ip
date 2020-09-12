import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * launches the javafx application
     * @param args arg to run
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
