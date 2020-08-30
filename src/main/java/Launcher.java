import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Runs Duke application.
     */
    public static void main(String[] args) {
        System.out.println("running from Launcher Class");
        Application.launch(Main.class, args);
    }
}
