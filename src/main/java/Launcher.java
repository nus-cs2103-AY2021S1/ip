import duke.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches Duke from the Main class.
     * @param args arguments to input.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
