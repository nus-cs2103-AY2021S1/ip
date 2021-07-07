import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main method to run the program.
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
