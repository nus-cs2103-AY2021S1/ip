import javafx.application.Application;

/**
 * Launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
