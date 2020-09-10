import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues, launch the GUI application.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
